Feature: Feed the RuleEngine

  Feed the rule engine by adding RuleProcessor to the system.

  Scenario: Add a RuleProcessor with 1 rule
    Given the RuleEngine is up and running with maxPoolSize="1"
    And I create a new RuleProcessor
    And I add a RuleSet named "test"
    And I add MyRule to the RuleSet "test"
    And I add MySecondRule to the RuleSet "test"
    And I create a new JsonObject data with "{'id':1, 'name':'myObject','age':25, 'weight':78,'height':170,'date':'2018-09-13 12:00:00.00T000','status':'unknown'}"
    Then Submit the RuleProcessor to the RuleEngine with RuleSet "test"
    And The JsonObject identified by "1" has been processed by RuleEngine and the "status" is "done"

  Scenario: Add a RuleProcessor with 2 rules and 5 data items
    Given the RuleEngine is up and running with maxPoolSize="4"
    And I create a new RuleProcessor
    And I add a RuleSet named "test-multi-rules"
    And I add MyRule to the RuleSet "test-multi-rules"
    And I add MySecondRule to the RuleSet "test-multi-rules"
    And I create a new JsonObject data with "{'id':1, 'name':'myObject-1','age':12, 'weight':20,'height':150,'date':'2018-09-13 12:00:00.00T000','status':'unknown'}"
    And I create a new JsonObject data with "{'id':2, 'name':'myObject-2','age':35, 'weight':70,'height':182,'date':'2018-09-13 12:00:00.00T000','status':'unknown'}"
    And I create a new JsonObject data with "{'id':3, 'name':'myObject-3','age':67, 'weight':78,'height':167,'date':'2018-09-13 12:00:00.00T000','status':'unknown'}"
    And I create a new JsonObject data with "{'id':4, 'name':'myObject-4','age':17, 'weight':66,'height':170,'date':'2018-09-13 12:00:00.00T000','status':'unknown'}"
    And I create a new JsonObject data with "{'id':5, 'name':'myObject-5','age':6, 'weight':56,'height':170,'date':'2018-09-13 12:00:00.00T000','status':'unknown'}"
    Then Submit the RuleProcessor to the RuleEngine with RuleSet "test-multi-rules"
    And The JsonObject identified by "1" has been processed by RuleEngine and the "tags" is "pediatric"
    And The JsonObject identified by "4" has been processed by RuleEngine and the "tags" is "pediatric"
    And The JsonObject identified by "5" has been processed by RuleEngine and the "tags" is "pediatric"

  Scenario: Add a RuleProcessor with 2 rules and large data from a json file
    Given the RuleEngine is up and running with maxPoolSize="4"
    And I create a new RuleProcessor
    And I add a RuleSet named "test-multi-rules"
    And I add MyRule to the RuleSet "test-multi-rules"
    And I add MySecondRule to the RuleSet "test-multi-rules"
    And I create new JsonObject data from file "tests/ruleengine-data.json"
    Then Submit the RuleProcessor to the RuleEngine with RuleSet "test-multi-rules"
    And The JsonObject identified by "1,4,5" have been processed by RuleEngine and the "tags" contains "pediatric"
