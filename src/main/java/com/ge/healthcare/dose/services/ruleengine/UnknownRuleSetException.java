package com.ge.healthcare.dose.services.ruleengine;

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
