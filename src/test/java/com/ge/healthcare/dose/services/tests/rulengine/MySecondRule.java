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
    public MySecondRule(String s) {
        super(s);
    }

    @Override
    public void run() {

    }

    @Override
    public boolean evaluate(RuleContext rc, JsonObject obj) {
        float age = obj.get("age").getAsFloat();
        return age < 18;
    }

    @Override
    public JsonObject process(RuleContext rc, JsonObject obj) {
        obj.addProperty("tags", "pediatric");
        return obj;
    }
}
