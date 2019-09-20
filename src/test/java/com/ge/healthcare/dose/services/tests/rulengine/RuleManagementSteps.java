package com.ge.healthcare.dose.services.tests.rulengine;

import com.ge.healthcare.dose.services.ruleengine.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cucumber.api.java8.En;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Cucumber Gherkin Steps implementation for the RuleProcessor tests.
 *
 * @author Frédéric Delorme<frederic.delorme@ge.com>
 */
@Slf4j
public class RuleManagementSteps implements En {

    Map<Integer, JsonObject> objects = new HashMap<>();
    private boolean unknownRuleSetExceptionRaised;
    private RuleEngine re;
    private RuleProcessor rp;
    private RuleSet rs;
    private RuleContext<String, Object> rc;
    private MyRule r;
    private JsonObject jsonObject;

    private JsonDataOutputWriter jsonDataOutPutWriter;


    static private int index = 0;

    public RuleManagementSteps() {

        jsonDataOutPutWriter = new JsonDataOutputWriter();


        Given("^the RuleProcessor instance is up and running$", () -> {

            rs = new RuleSet("test");
            rp = new RuleProcessor(rs);
            rc = new RuleContext<>();
            rp.setContext(rc);

        });

        Given("^I add a new Rule$", () -> {
            r = new MyRule("this-is-my-rule");
            rs.add(r);
            rp.add(rs);
        });

        Then("^the New Rule is listed in RuleProcessor rules list$", () -> {
            try {
                Rule rf = rp.getRule("test", "this-is-my-rule");
                assertEquals("the Find rules from RuleProcessor do not correspond to the one added", rf.getName(), r.getName());
            } catch (UnknownRuleException ure) {
                log.error("Unable to get the corresdponding Rule", ure);
            }
        });

        And("^I add a new RuleSet$", () -> {
            RuleSet rs2 = new RuleSet("test2");
            rp.add(rs2);
        });

        Then("^the New RuleSet is listed in RuleProcessor ruleSet list$", () -> {
            RuleSet rs3 = rp.getRuleSets().get("test2");
            assertEquals("The RuleSet test2 has not been found from RuleProcessor", "test2", rs3.getName());
        });
        And("^I try to get an unknown Role$", () -> {
            Rule r2 = new MyRule("notAdded");
        });

        Then("^the UnknownRuleException is raised$", () -> {
            try {
                Rule rf = rp.getRule("test", "unknown");
            } catch (UnknownRuleException ure) {
                assertEquals("the Rule is unknown, the UnknownRuleException is raised", ure.getMessage(), "Rule test:unknown is unknown");
            }
        });

        And("^I create a new JsonObject data with \"([^\"]*)\"$", (String jsonValue) -> {
            JsonObject obj = new JsonParser().parse(jsonValue).getAsJsonObject();
            jsonObject = obj;
            objects.put(obj.get("id").getAsInt(), obj);
        });

        Then("^I process the JsonObject with the RuleProcessor with RuleSet name is \"([^\"]*)\"$", (String ruleSetName) -> {
            rp.setDataWriter(jsonDataOutPutWriter);
            rp.process(ruleSetName, objects.values());
            rp.run();
        });

        And("^The JsonObject identified by \"([^\"]*)\" is processed and the \"([^\"]*)\" is \"([^\"]*)\"$", (String jsonObjectId, String attributeName, String requestStatus) -> {
            // waitFor(500);
            int iJsonObjectId = Integer.parseInt(jsonObjectId);
            JsonObject jsonObject = jsonDataOutPutWriter.getById(iJsonObjectId);
            String status = jsonObject.get(attributeName).getAsString();
            assertEquals(String.format("The jsonObject %s has not been processed; [%s] is [%s]", jsonObjectId, attributeName, status), requestStatus, status);
        });

        And("^The JsonObject identified by \"([^\"]*)\" has been processed by RuleEngine and the \"([^\"]*)\" is \"([^\"]*)\"$", (String jsonObjectId, String attributeName, String requestStatus) -> {
            //  waitFor(500);
            int iJsonObjectId = Integer.parseInt(jsonObjectId);
            OutputDataWriter odw = re.getDataWriter();
            JsonObject jsonObject = (JsonObject) odw.getById(iJsonObjectId);
            String status = jsonObject.get(attributeName).getAsString();
            assertEquals(String.format("The jsonObject %s has not been processed; [%s] is [%s]", jsonObjectId, attributeName, status), requestStatus, status);
        });
        And("^I remove a Rule named \"([^\"]*)\"$", (String ruleName) -> {
            rs.remove(ruleName);
        });

        Then("^the \"([^\"]*)\" Rule does not exists anymore$", (String ruleName) -> {
            try {
                rp.getRule("test", ruleName);
            } catch (UnknownRuleException ure) {
                assertEquals("the Rule is unknown, the UnknownRuleException is raised", ure.getMessage(), "Rule test:" + ruleName + " is unknown");
            }
        });

        And("^I remove a RuleSet named \"([^\"]*)\"$", (String ruleSetName) -> {
            rp.getRuleSets().remove(ruleSetName);
        });

        Then("^the set of Rules \"([^\"]*)\" does not exists anymore$", (String ruleSetName) -> {
            try {
                rp.getRuleSet(ruleSetName);
            } catch (UnknownRuleSetException urse) {
                assertEquals("the RuleSet is unknown, the UnknownRuleSetException is raised", urse.getMessage(), "RuleSet " + ruleSetName + " is unknown");
            }
        });

        And("^I try to get an \"([^\"]*)\" RuleSet$", (String ruleSetName) -> {
            try {
                rs = rp.getRuleSet(ruleSetName);
            } catch (UnknownRuleSetException e) {
                unknownRuleSetExceptionRaised = true;
            }
        });
        Then("^the UnknownRuleSetException is raised$", () -> {
            assertTrue("The UnknownRuleSetException has not been raised", unknownRuleSetExceptionRaised);
        });

        Given("^the RuleEngine is up and running with maxPoolSize=\"([^\"]*)\"$", (String maxPoolSize) -> {
            int iMaxPoolSize = Integer.parseInt(maxPoolSize);
            re = new RuleEngine(iMaxPoolSize);
            rc = new RuleContext<>();
            re.setOutputDataWrite(jsonDataOutPutWriter);
        });
        And("^I create a new RuleProcessor$", () -> {
            rp = new RuleProcessor();
        });
        And("^I add a RuleSet named \"([^\"]*)\"$", (String ruleSetName) -> {
            rs = new RuleSet(ruleSetName);
            rp.add(rs);
        });
        And("^I add MyRule to the RuleSet \"([^\"]*)\"$", (String ruleSetName) -> {
            MyRule r = new MyRule("test");
            rs.add(r);
        });
        Then("^Submit the RuleProcessor to the RuleEngine with RuleSet \"([^\"]*)\"$", (String ruleSetName) -> {
            addProcessorRoRuleEngine(ruleSetName);
        });
        And("^I add MySecondRule to the RuleSet \"([^\"]*)\"$", (String ruleSetName) -> {
            MySecondRule r = new MySecondRule("rule-test2");
            rs.add(r);
        });
        And("^I add TagWithThresholdOnIntRule to the RuleSet \"([^\"]*)\"$", (String ruleSetname) -> {
            TagWithThresholdOnIntRule r = new TagWithThresholdOnIntRule("rule-use-context");
            rs.add(r);
        });
        And("^I put \"([^\"]*)\" in the RuleContext \"([^\"]*)\"$", (String jsonValue, String contextKeyName) -> {
            JsonObject obj = new JsonParser().parse(jsonValue).getAsJsonObject();
            rc.put(contextKeyName, obj);
        });
    }

    private void addProcessorRoRuleEngine(String ruleSetName) {
        try {
            rp.setContext(rc);
            rp.process(ruleSetName, objects.values());
            re.addRuleProcessor(rp);
            Thread.sleep(250);
        } catch (NoMoreExecutorPoolSlotException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitFor(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
