package com.ge.healthcare.dose.services.tests.rulengine;

import com.ge.healthcare.dose.services.ruleengine.AbstractRule;
import com.ge.healthcare.dose.services.ruleengine.Rule;
import com.ge.healthcare.dose.services.ruleengine.RuleContext;
import com.google.gson.JsonObject;

/**
 * A test Rule implemented only for test purpose.
 *
 * @author Frédéric Delorme<frederic.delorme@ge.com>
 * @see AbstractRule
 * @see Rule
 */
public class MyRule extends AbstractRule implements Rule {

    /**
     * Main contructor for this Rule.
     *
     * @param ruleName the name for this Rule.
     */
    public MyRule(String ruleName) {
        super(ruleName);
    }

    /**
     * Detect if the the current patient object is already processed.
     *
     * @param rc  the RuleContext to help and feed evaluate the trigger
     * @param obj the object to be evaluated regarding rule context.
     * @return
     */
    @Override
    public boolean evaluate(RuleContext rc, JsonObject obj) {
        return obj.get("status").getAsString().equals("unknown");
    }

    /**
     * Compute the BMI for this patient.
     * @param rc  the execution RuleContext to be used as an input/output gateway.
     * @param obj the object to be processed.
     * @return
     */
    @Override
    public JsonObject process(RuleContext rc, JsonObject obj) {

        float weight = obj.get("weight").getAsFloat();
        float height = obj.get("height").getAsFloat();
        float bmi = height / weight;

        obj.addProperty("bmi", bmi);
        obj.addProperty("status", "done");

        return obj;
    }
}
