package com.ge.healthcare.dose.services.tests.rulengine;

import com.ge.healthcare.dose.services.ruleengine.AbstractRule;
import com.ge.healthcare.dose.services.ruleengine.Rule;
import com.ge.healthcare.dose.services.ruleengine.RuleContext;
import com.google.gson.JsonObject;

/**
 * A test Rule implemented only for test purpose.
 * This rule detect if patient is pediatric relevant.
 *
 * @author Frédéric Delorme<frederic.delorme@ge.com>
 * @see AbstractRule
 * @see Rule
 */
public class MySecondRule extends AbstractRule implements Rule {
    /**
     * Default constructor inherited from AbstractRule.
     *
     * @param ruleName name of the Rule.
     */
    public MySecondRule(String ruleName) {
        super(ruleName);
    }

    /**
     * Evaluate age of the patient object to define if he is pediatric relevant.
     *
     * @param rc  the RuleContext to help and feed evaluate the trigger
     * @param obj the object to be evaluated regarding rule context.
     * @return
     */
    @Override
    public boolean evaluate(RuleContext rc, JsonObject obj) {
        float age = obj.get("age").getAsFloat();
        return age < 18;
    }

    /**
     * Apply the 'pediatric' tag to the patient object.
     * @param rc  the execution RuleContext to be used as an input/output gateway.
     * @param obj the object to be processed.
     * @return
     */
    @Override
    public JsonObject process(RuleContext rc, JsonObject obj) {
        obj.addProperty("tags", "pediatric");
        return obj;
    }
}
