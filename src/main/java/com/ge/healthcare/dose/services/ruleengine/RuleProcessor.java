package com.ge.healthcare.dose.services.ruleengine;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * This Small implementation is a prototype for a simple RuleProcessor.
 * <p>
 * It intends to evaluate JsonObject regarding some set of rules, and the execute the Rule processing if needed.
 * <p>
 * basic usage :
 * <pre>
 *     RuleProcessor rp = new RuleProcessor();
 *     RuleContext rc = new RuleContext();
 *     RuleSet rs = new RuleSet("My-Own-RuleSet");
 *
 *     MyRule r = new MyRule();
 *     rs.add(r);
 *
 *     rp.setContext(rc);
 *     rp.add(rs);
 *
 *     JsonObject myObject = new JsonObject();
 *
 *     // define data to be processed by the RuleSet.
 *     rp.process("My-Own-RuleSet",myObject);
 *     // execute the rule processing
 *     rp.run()
 *
 * </pre>()
 *
 * @author Frédéric Delorme<frederic.delorme@ge.com>
 * @set RuleContext
 * @see Rule
 * @see RuleSet
 */
@Slf4j
public class RuleProcessor implements Runnable {

    /**
     * Internal RuleSet store.
     */
    private Map<String, RuleSet> rulesSet = new HashMap<>();

    /**
     * name of the RuleSet to be used in this processor.
     */
    private String ruleSetName = "";

    /**
     * objects to be processed.
     */
    private List<JsonObject> objects = new ArrayList<>();

    /**
     * Internal RuleContext to be passed to all Rule during Rule evaluation and Rule execution.
     */
    private RuleContext ruleContext = new RuleContext<String, Object>();

    /**
     * The result writer implementation to be used from RuleProcessor's.
     */
    private OutputDataWriter odw;

    /**
     * Create a Rule processor.
     *
     * @param rs defin the RuleSet to be used by this particular processor.
     */
    public RuleProcessor(RuleSet rs) {
        this.rulesSet.put(rs.getName(), rs);
    }

    /**
     * Create a Rule processor.
     */
    public RuleProcessor() {
    }

    /**
     * The process method intends to evaluate all the activated RuleSet rule trigger and execute the corresponding rule process.
     *
     * @param ruleSetName the RuleSet name to be used for Rule processing on the object.
     * @param objects     the object's list to be evaluate regarding the RuleSet.
     */
    public void process(String ruleSetName, Collection<JsonObject> objects) {
        this.ruleSetName = ruleSetName;
        this.objects.clear();
        this.objects.addAll(objects);
    }

    /**
     * Add a RuleSet to the Engine.
     *
     * @param rs the RuleSet to be added.
     */
    public void add(RuleSet rs) {
        rulesSet.put(rs.getName(), rs);
    }

    /**
     * Defin ethe RuleProcessor context for rule processing.
     *
     * @param rc the new RuleContext to be used by all Rules.
     */
    public void setContext(RuleContext rc) {
        this.ruleContext = rc;
    }

    public RuleContext getRuleContext() {
        return this.ruleContext;
    }

    /**
     * apply rules from <code>ruleSetName</code> on <code>objects</code>, and store resulting data with
     * the <code>odw</code> implementation.
     */
    @Override
    public void run() {
        if (rulesSet.containsKey(this.ruleSetName)) {
            log.debug("Using RuleSetRule {}", this.ruleSetName);
            for (JsonObject object : objects)
                for (Map.Entry<String, Rule> ruleEntry : rulesSet.get(this.ruleSetName).entrySet()) {
                    Rule rule = ruleEntry.getValue();
                    if (rule.evaluate(ruleContext, object)) {
                        object = rule.process(ruleContext, object);
                        odw.writeData(object);
                        log.debug("Evaluate Rule {} for {}", ruleEntry.getKey(), object.toString());
                    }
                }
        }
    }

    /**
     * Define the OutputDataWriter for this RuleProcessor.
     *
     * @param dataWriter the OutputDataWriter implementation to use to store result from processing.
     */
    public void setDataWriter(OutputDataWriter dataWriter) {
        odw = dataWriter;
    }

    /**
     * return the map of RuleSet declared for this RuleProcessor.
     *
     * @return a map of RuleSet with String as key.
     */
    public Map<String, RuleSet> getRuleSets() {
        return this.rulesSet;
    }

    /**
     * Return all the objects attached to this RuleProcessor to be processed.
     *
     * @return a list of JsonObject to be evaluated and processed by this RuleProcessor.
     */
    public List<JsonObject> getObjects() {
        return objects;
    }

    /**
     * Return the Rule identified by its <code>ruleName</code> in the RuleSet <code>ruleSetName</code>
     *
     * @param ruleSetName the RuleSet the looking at rule is belonging to.
     * @param ruleName    the name of the Rule to e retrieved.
     * @return
     * @throws UnknownRuleException
     */
    public Rule getRule(String ruleSetName, String ruleName) throws UnknownRuleException {
        if (rulesSet.containsKey(ruleSetName)) {
            if (rulesSet.get(ruleSetName).containsKey(ruleName)) {
                return rulesSet.get(ruleSetName).get(ruleName);
            }
        }
        throw new UnknownRuleException(ruleSetName, ruleName);
    }

    /**
     * Return the RuleSet named ruleSetName from the map of RulesSet.
     *
     * @param ruleSetName the name of the RuleSet to be retrieved.
     * @return the RuleSet object.
     * @throws UnknownRuleSetException
     */
    public RuleSet getRuleSet(String ruleSetName) throws UnknownRuleSetException {
        if (rulesSet.containsKey(ruleSetName)) {

            return rulesSet.get(ruleSetName);
        }
        throw new UnknownRuleSetException(ruleSetName);
    }
}
