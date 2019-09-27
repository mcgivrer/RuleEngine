package com.ge.healthcare.dose.services.ruleengine;

import com.google.gson.JsonObject;

/**
 * The Rule interface is the basic entity description to be managed by all the RuleProcessor process.
 * <p></p>It provides 2 main methods signatures :
 * <ul>
 * <li>evaluate() to detect if this rule can match with trigger condition,</li>
 * <li>process() to appluy any change to any data according to the fact this rule is triggered.</li>
 * </ul>
 * <p>
 * This Rule is managed by the {@link RuleProcessor#process(String, JsonObject)} method.
 */
public interface Rule {
    String getName();

    /**
     * the evaluate method purpose consists in rule trigger evaluation.
     * If true, the rule process method will be executed by the RuleProcessor.
     *
     * @param rc  the RuleContext to help and feed evaluate the trigger
     * @param obj the object to be evaluated regarding rule context.
     * @return
     */
    boolean evaluate(RuleContext rc, JsonObject obj);

    /**
     * The process to be apply when this rule is triggered.
     *
     * @param rc  the execution RuleContext to be used as an input/output gateway.
     * @param obj the object to be processed.
     * @return an int value corresponding to an error code. 0 is OK, any other error will be evaluate as an error code.
     */
    JsonObject process(RuleContext rc, JsonObject obj);

}
