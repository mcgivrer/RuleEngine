package com.ge.healthcare.dose.services.ruleengine;

/**
 * This exception is raised when a non existent RuleSet is find.
 *
 * @author Frédéric Delorme<frederic.Delorme@ge.com>
 * @since 2019
 */
public class UnknownRuleSetException extends Exception {
    private String ruleSetName;

    /**
     * Define the UnknownRuleException for looking for rule into the ruleSet set.
     *
     * @param ruleSetName
     */
    public UnknownRuleSetException(String ruleSetName) {
        this.ruleSetName = ruleSetName;
    }

    /**
     * New exception message.
     *
     * @return
     */
    @Override
    public String getMessage() {
        return String.format("RuleSet %s is unknown", ruleSetName);
    }
}
