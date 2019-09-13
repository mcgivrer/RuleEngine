package com.ge.healthcare.dose.services.ruleengine;

import java.util.HashMap;

/**
 * this object define a set of Rules to be uised in a RuleProcessor instance.
 *
 * @author Frédéric Delorme<frederic.delorme@ge.com>
 * @see Rule
 */
public class RuleSet extends HashMap<String, Rule> {
    private String name;

    /**
     * Initialize a new RuleSet with a <code>name</code>.
     *
     * @param name the name of the new RuleSet.
     */
    public RuleSet(String name) {
        super();
        this.name = name;
    }

    /**
     * return the name of this RuleSet instance.
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Add a {@link Rule} to the RuleSet.
     *
     * @param r the Rule to be added.
     */
    public void add(Rule r) {
        this.put(r.getName(), r);
    }
}
