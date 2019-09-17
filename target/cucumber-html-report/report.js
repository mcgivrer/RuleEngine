$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("ruleengine-rulemanagement.feature");
formatter.feature({
  "line": 1,
  "name": "RuleProcessor Manage Rules",
  "description": "\nAdd or manage Rule into the RuleProcessor.",
  "id": "ruleprocessor-manage-rules",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Add a Rule to the RuleProcessor",
  "description": "",
  "id": "ruleprocessor-manage-rules;add-a-rule-to-the-ruleprocessor",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "the RuleProcessor instance is up and running",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I add a new Rule",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "the New Rule is listed in RuleProcessor rules list",
  "keyword": "Then "
});
formatter.match({
  "location": "RuleManagementSteps.java:35"
});
formatter.result({
  "duration": 481649003,
  "status": "passed"
});
formatter.match({
  "location": "RuleManagementSteps.java:44"
});
formatter.result({
  "duration": 131374,
  "status": "passed"
});
formatter.match({
  "location": "RuleManagementSteps.java:50"
});
formatter.result({
  "duration": 1932673,
  "status": "passed"
});
formatter.scenario({
  "line": 10,
  "name": "Retrieve a non existing Rule from the RuleProcessor",
  "description": "",
  "id": "ruleprocessor-manage-rules;retrieve-a-non-existing-rule-from-the-ruleprocessor",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 11,
  "name": "the RuleProcessor instance is up and running",
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "I try to get an unknown Role",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "the UnknownRuleException is raised",
  "keyword": "Then "
});
formatter.match({
  "location": "RuleManagementSteps.java:35"
});
formatter.result({
  "duration": 37226,
  "status": "passed"
});
formatter.match({
  "location": "RuleManagementSteps.java:68"
});
formatter.result({
  "duration": 50286,
  "status": "passed"
});
formatter.match({
  "location": "RuleManagementSteps.java:72"
});
formatter.result({
  "duration": 370094,
  "status": "passed"
});
formatter.scenario({
  "line": 15,
  "name": "Remove a Rule",
  "description": "",
  "id": "ruleprocessor-manage-rules;remove-a-rule",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 16,
  "name": "the RuleProcessor instance is up and running",
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "I remove a Rule named \"test-rule\"",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "the \"test-rule\" Rule does not exists anymore",
  "keyword": "Then "
});
formatter.match({
  "location": "RuleManagementSteps.java:35"
});
formatter.result({
  "duration": 41975,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test-rule",
      "offset": 23
    }
  ],
  "location": "RuleManagementSteps.java:96"
});
formatter.result({
  "duration": 6883298,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test-rule",
      "offset": 5
    }
  ],
  "location": "RuleManagementSteps.java:100"
});
formatter.result({
  "duration": 491410,
  "status": "passed"
});
formatter.uri("ruleengine-ruleset-mgt.feature");
formatter.feature({
  "line": 1,
  "name": "RuleProcessor Manage RuleSets",
  "description": "\nAdd or manage RuleSet into the RuleProcessor.",
  "id": "ruleprocessor-manage-rulesets",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 6,
  "name": "Add a RuleSet",
  "description": "",
  "id": "ruleprocessor-manage-rulesets;add-a-ruleset",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "the RuleProcessor instance is up and running",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I add a new RuleSet",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "the New RuleSet is listed in RuleProcessor ruleSet list",
  "keyword": "Then "
});
formatter.match({
  "location": "RuleManagementSteps.java:35"
});
formatter.result({
  "duration": 47493,
  "status": "passed"
});
formatter.match({
  "location": "RuleManagementSteps.java:59"
});
formatter.result({
  "duration": 70611,
  "status": "passed"
});
formatter.match({
  "location": "RuleManagementSteps.java:64"
});
formatter.result({
  "duration": 70959,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Retrieve a non existing RuleSet from the RuleProcessor",
  "description": "",
  "id": "ruleprocessor-manage-rulesets;retrieve-a-non-existing-ruleset-from-the-ruleprocessor",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 12,
  "name": "the RuleProcessor instance is up and running",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "I try to get an \"unknown\" RuleSet",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "the UnknownRuleSetException is raised",
  "keyword": "Then "
});
formatter.match({
  "location": "RuleManagementSteps.java:35"
});
formatter.result({
  "duration": 41347,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "unknown",
      "offset": 17
    }
  ],
  "location": "RuleManagementSteps.java:120"
});
formatter.result({
  "duration": 273083,
  "status": "passed"
});
formatter.match({
  "location": "RuleManagementSteps.java:127"
});
formatter.result({
  "duration": 4101972,
  "status": "passed"
});
formatter.scenario({
  "line": 16,
  "name": "Remove a RuleSet",
  "description": "",
  "id": "ruleprocessor-manage-rulesets;remove-a-ruleset",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 17,
  "name": "the RuleProcessor instance is up and running",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "I remove a RuleSet named \"test-ruleset\"",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "the set of Rules \"test-ruleset\" does not exists anymore",
  "keyword": "Then "
});
formatter.match({
  "location": "RuleManagementSteps.java:35"
});
formatter.result({
  "duration": 41905,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test-ruleset",
      "offset": 26
    }
  ],
  "location": "RuleManagementSteps.java:108"
});
formatter.result({
  "duration": 133818,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test-ruleset",
      "offset": 18
    }
  ],
  "location": "RuleManagementSteps.java:112"
});
formatter.result({
  "duration": 9184250,
  "status": "passed"
});
formatter.uri("ruleengine-submit.feature");
formatter.feature({
  "line": 1,
  "name": "Feed the RuleEngine",
  "description": "\nFeed the rule engine by adding RuleProcessor to the system.",
  "id": "feed-the-ruleengine",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Add a RuleProcessor",
  "description": "",
  "id": "feed-the-ruleengine;add-a-ruleprocessor",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "the RuleEngine is up and running with maxPoolSize\u003d\"1\"",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I create a new RuleProcessor",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I add a RuleSet named \"test\"",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I add MyRule to the RuleSet \"test\"",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I create a new JsonObject with \"{\u0027name\u0027:\u0027myObject\u0027,\u0027age\u0027:25, \u0027weight\u0027:78,\u0027height\u0027:170,\u0027date\u0027:\u00272018-09-13 12:00:00.00T000\u0027,\u0027status\u0027:\u0027unknown\u0027}\"",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Submit the RuleProcessor to the RuleEngine",
  "keyword": "Then "
});
formatter.step({
  "line": 12,
  "name": "The JsonObject named \"myObject\" is processed and the status is \"done\"",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 51
    }
  ],
  "location": "RuleManagementSteps.java:133"
});
formatter.result({
  "duration": 402818482,
  "status": "passed"
});
formatter.match({
  "location": "RuleManagementSteps.java:137"
});
formatter.result({
  "duration": 66071,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test",
      "offset": 23
    }
  ],
  "location": "RuleManagementSteps.java:140"
});
formatter.result({
  "duration": 138986,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test",
      "offset": 29
    }
  ],
  "location": "RuleManagementSteps.java:144"
});
formatter.result({
  "duration": 163012,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "{\u0027name\u0027:\u0027myObject\u0027,\u0027age\u0027:25, \u0027weight\u0027:78,\u0027height\u0027:170,\u0027date\u0027:\u00272018-09-13 12:00:00.00T000\u0027,\u0027status\u0027:\u0027unknown\u0027}",
      "offset": 32
    }
  ],
  "location": "RuleManagementSteps.java:80"
});
formatter.result({
  "duration": 38628749,
  "status": "passed"
});
formatter.match({
  "location": "RuleManagementSteps.java:148"
});
formatter.result({
  "duration": 2018909477,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "myObject",
      "offset": 22
    },
    {
      "val": "done",
      "offset": 64
    }
  ],
  "location": "RuleManagementSteps.java:89"
});
formatter.result({
  "duration": 14493169,
  "status": "passed"
});
formatter.uri("ruleprocessor-process.feature");
formatter.feature({
  "line": 1,
  "name": "RuleEngine processing",
  "description": "\ntry and execute processing JsonObject with some rules",
  "id": "ruleengine-processing",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Create and feed an object to RuleEngine",
  "description": "",
  "id": "ruleengine-processing;create-and-feed-an-object-to-ruleengine",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "the RuleProcessor instance is up and running",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I add a new RuleSet",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I add a new Rule",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I create a new JsonObject with \"{\u0027name\u0027:\u0027myObject\u0027,\u0027age\u0027:25, \u0027weight\u0027:78,\u0027height\u0027:170,\u0027date\u0027:\u00272018-09-13 12:00:00.00T000\u0027,\u0027status\u0027:\u0027unknown\u0027}\"",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I process the JsonObject named \"myObject\" with the RuleProcessor with RuleSet name is \"test\"",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "the JsonObject named \"myObject\" is processed and the status is \"done\"",
  "keyword": "And "
});
formatter.match({
  "location": "RuleManagementSteps.java:35"
});
formatter.result({
  "duration": 181730,
  "status": "passed"
});
formatter.match({
  "location": "RuleManagementSteps.java:59"
});
formatter.result({
  "duration": 42045,
  "status": "passed"
});
formatter.match({
  "location": "RuleManagementSteps.java:44"
});
formatter.result({
  "duration": 31150,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "{\u0027name\u0027:\u0027myObject\u0027,\u0027age\u0027:25, \u0027weight\u0027:78,\u0027height\u0027:170,\u0027date\u0027:\u00272018-09-13 12:00:00.00T000\u0027,\u0027status\u0027:\u0027unknown\u0027}",
      "offset": 32
    }
  ],
  "location": "RuleManagementSteps.java:80"
});
formatter.result({
  "duration": 266867,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "myObject",
      "offset": 32
    },
    {
      "val": "test",
      "offset": 87
    }
  ],
  "location": "RuleManagementSteps.java:85"
});
formatter.result({
  "duration": 139126,
  "status": "passed"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});