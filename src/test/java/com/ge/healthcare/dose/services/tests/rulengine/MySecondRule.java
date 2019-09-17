package com.ge.healthcare.dose.services.tests.rulengine;

import com.ge.healthcare.dose.services.ruleengine.AbstractRule;
import com.ge.healthcare.dose.services.ruleengine.Rule;
import com.ge.healthcare.dose.services.ruleengine.RuleContext;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A test Rule implemented only for test purpose.
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
        return obj.get("status").getAsString().equals("unknown");
    }

    @Override
    public JsonObject process(RuleContext rc, JsonObject obj) {
        float weight = obj.get("weight").getAsFloat();
        if (weight < 25) {
            obj.addProperty("tags", "pediatric");
        }
        return obj;
    }
}
