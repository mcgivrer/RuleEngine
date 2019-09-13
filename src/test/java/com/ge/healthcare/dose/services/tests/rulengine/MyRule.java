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
    public MyRule(String s) {
        super(s);
    }

    @Override
    public void run() {

    }

    @Override
    public boolean evaluate(RuleContext rc, JsonObject obj) {
        return obj.get("status").getAsString().equals("unknown");
    }

    @Override
    public int process(RuleContext rc, JsonObject obj) {
        float weight = obj.get("weight").getAsFloat();
        float height = obj.get("height").getAsFloat();
        float bmi = height / weight;
        obj.addProperty("bmi", bmi);
        obj.addProperty("status", "done");

        return 0;
    }
}
