Feature: Feed the RuleEngine

  Feed the rule engine by adding RuleProcessor to the system.

  Scenario: Add a RuleProcessor
    Given the RuleEngine is up and running with maxPoolSize="1"
    And I create a new RuleProcessor
    And I add a RuleSet named "test"
    And I add MyRule to the RuleSet "test"
    And I create a new JsonObject with "{'name':'myObject','age':25, 'weight':78,'height':170,'date':'2018-09-13 12:00:00.00T000','status':'unknown'}"
    Then Submit the RuleProcessor to the RuleEngine
    And The JsonObject named "myObject" is processed and the status is "done"