package com.ge.healthcare.dose.services.tests.rulengine;

import com.ge.healthcare.dose.services.ruleengine.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cucumber.api.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private boolean unknownRuleExcpetionRaised;
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


        Given("^a RuleProcessor instance is created$", () -> {
            rp = new RuleProcessor();
        });

        Given("^the RuleProcessor instance is up and running$", () -> {
            assertTrue("RuleProcessor's RuleSet is empty", !rp.getRuleSets().isEmpty());
        });

        And("^the RuleProcessor RuleSet map is not empty$", () -> {
            assertTrue("RuleProcessor's RuleSet is empty", !rp.getRuleSets().isEmpty());
        });

        Given("^I add a new Rule named \"([^\"]*)\"$", (String ruleName) -> {
            r = new MyRule(ruleName);
            rs.add(r);
            rp.add(rs);
        });

        Given("^I add a new Rule named \"([^\"]*)\" to RuleSet \"([^\"]*)\"$", (String ruleName, String ruleSetName) -> {
            try {
                r = new MyRule(ruleName);
                RuleSet rs2 = null;
                rs2 = rp.getRuleSet(ruleSetName);
                rs2.add(r);
                rp.add(rs2);
            } catch (UnknownRuleSetException e) {
                e.printStackTrace();
            }
        });

        Then("^the New Rule \"([^\"]*)\" is listed in RuleProcessor rules list$", (String ruleName) -> {
            try {
                Rule rf = rp.getRule("test", "this-is-my-rule");
                assertEquals("the Find rules from RuleProcessor do not correspond to the one added", rf.getName(), r.getName());
            } catch (UnknownRuleException ure) {
                log.error("Unable to get the corresdponding Rule", ure);
            }
        });

        And("^I add a new RuleSet named \"([^\"]*)\"$", (String ruleSetName) -> {
            RuleSet rs2 = new RuleSet(ruleSetName);
            rp.add(rs2);
        });

        Then("^the New RuleSet \"([^\"]*)\" is listed in RuleProcessor ruleSet list$", (String ruleSetName) -> {
            RuleSet rs3 = rp.getRuleSets().get(ruleSetName);
            assertEquals("The RuleSet test2 has not been found from RuleProcessor", "test2", rs3.getName());
        });
        And("^I try to get an \"([^\"]*)\" Rule from the RuleSet \"([^\"]*)\"$", (String ruleName, String ruleSetName) -> {
            try {
                Rule rf = rp.getRule(ruleSetName, ruleName);
            } catch (UnknownRuleException ure) {
                unknownRuleExcpetionRaised = true;
            }
        });

        Then("^the UnknownRuleException is raised$", () -> {
            assertTrue("the Rule is unknown, the UnknownRuleException is raised", unknownRuleExcpetionRaised);
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
        And("^I remove a Rule named \"([^\"]*)\" from RuleSet \"([^\"]*)\"$", (String ruleName, String ruleSetName) -> {
            RuleSet rsItem = null;
            try {
                rsItem = rp.getRuleSet(ruleSetName);
                rsItem.remove(ruleName);
            } catch (UnknownRuleSetException e) {
                e.printStackTrace();
            }
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

        And("^I declare a RuleContext to the RuleProcessor$", () -> {
            rc = new RuleContext<>();
            rp.setContext(rc);
        });

        And("^I create new JsonObject data from file \"([^\"]*)\"$", this::readDataFromJsonFile);

        And("^The JsonObject identified by \"([^\"]*)\" have been processed by RuleEngine and the \"([^\"]*)\" contains \"([^\"]*)\"$", (String jsonIdList, String attributeName, String attributeValue) -> {
            compareObjectsAttributeValue(jsonIdList, attributeName, attributeValue);
        });


    }

    private void compareObjectsAttributeValue(String jsonIdList, String attributeName, String attributeValue) {
        for (String jsonId : jsonIdList.split(",")) {
            int jId = Integer.parseInt(jsonId);
            JsonObject o = objects.get(jId);
            JsonElement eStatus = o.get(attributeName);
            String status = "unknown-attribute";
            if (o != null && status != null) {
                status = eStatus.getAsString();
            }

            String message = String.format("The Json object with id=%d's attributename '%s' does not contains '%s'", jId, attributeName, attributeValue);
            Assert.assertTrue(message, status.contains(attributeValue));
        }
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

    private void readDataFromJsonFile(String filePath) {
        try {
            Path path = Paths.get(RuleManagementSteps.class.getClassLoader().getResource(File.separator + filePath).toURI());
            byte[] jsonDataLines = Files.readAllBytes(path);
            String jsonText = new String(jsonDataLines);
            JsonArray jsonData = new JsonParser().parse(jsonText).getAsJsonArray();

            for (JsonElement jsonValue : jsonData) {
                JsonObject obj = jsonValue.getAsJsonObject();
                objects.put(obj.get("id").getAsInt(), obj);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
