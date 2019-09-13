package com.ge.healthcare.dose.services.ruleengine;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This Small implementation is a prototype for a simple RuleProcessor.
 * <p>
 * It intends to evaluate JsonObject regarding some set of rules, and the execute the Rule processing if needed.
 * <p>
 * basic usage :
 * <pre>
 *     RuleProcessor re = new RuleProcessor();
 *     RuleContext rc = new RuleContext();
 *     RuleSet rs = new RuleSet("My-Own-RuleSet");
 *
 *     MyRule r = new MyRule();
 *     rs.add(r);
 *
 *     re.setContext(rc);
 *     re.add(rs);
 *
 *     JsonObject myObject = new JsonObject();
 *
 *     re.process("My-Own-RuleSet",myObject);
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
    Map<String, RuleSet> rulesSet = new HashMap<>();

    /**
     * name of the RuleSet to be used in this processor.
     */
    String ruleSetName = "";

    /**
     * objects to be processed.
     */
    Collection<JsonObject> objects = new ArrayList<>();

    /**
     * Internal RuleContext to be passed to all Rule during Rule evaluation and Rule execution.
     */
    RuleContext ruleContext = new RuleContext<String, Object>();


    /**
     * Create a Rule processor.
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
        this.objects = objects;
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

    public Map<String, RuleSet> getRuleSets() {
        return this.rulesSet;
    }

    public Rule getRule(String ruleSetName, String ruleName) throws UnknownRuleException {
        if (rulesSet.containsKey(ruleSetName)) {
            if (rulesSet.get(ruleSetName).containsKey(ruleName)) {
                return rulesSet.get(ruleSetName).get(ruleName);
            }
        }
        throw new UnknownRuleException(ruleSetName, ruleName);
    }

    public RuleSet getRuleSet(String ruleSetName) throws UnknownRuleSetException {
        if (rulesSet.containsKey(ruleSetName)) {

            return rulesSet.get(ruleSetName);
        }
        throw new UnknownRuleSetException(ruleSetName);
    }

    @Override
    public void run() {
        for (JsonObject object : objects)
            if (rulesSet.containsKey(this.ruleSetName)) {
                log.debug("Using RuleSetRule {}", this.ruleSetName);
                for (Map.Entry<String, Rule> ruleEntry : rulesSet.get(this.ruleSetName).entrySet()) {
                    Rule rule = ruleEntry.getValue();
                    if (rule.evaluate(ruleContext, object)) {
                        rule.process(ruleContext, object);
                        log.debug("Evaluate Rule {} for {}", ruleEntry.getKey(), object.toString());
                    }
                }
            }
    }
}
