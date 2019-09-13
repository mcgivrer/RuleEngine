package com.ge.healthcare.dose.services.ruleengine;

/**
 * Exception raised when user attempts to retrieve an an unknown Rule from the RuleProcessor.
 *
 * @author Frédéric Delorme<frederic.delorme@ge.com>
 */
public class UnknownRuleException extends Exception {
    private String ruleSetName;
    private String ruleName;


    /**
     * Define the UnknownRuleException for looking for rule into the ruleSet set.
     *
     * @param ruleSetName
     * @param ruleName
     */
    public UnknownRuleException(String ruleSetName, String ruleName) {
        this.ruleSetName = ruleSetName;
        this.ruleName = ruleName;
    }

    /**
     * New exception message.
     *
     * @return
     */
    @Override
    public String getMessage() {
        return String.format("Rule %s:%s is unknown", ruleSetName, ruleName);
    }
}
