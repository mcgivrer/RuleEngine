Feature: RuleEngine processing

  try and execute processing JsonObject with some rules

  @tag: RuleEngine
  Scenario: Create and feed an object to RuleEngine
    Given the RuleProcessor instance is up and running
    And I add a new RuleSet
    And I add a new Rule
    And I create a new JsonObject with "{'name':'myObject','age':25, 'weight':78,'height':170,'date':'2018-09-13 12:00:00.00T000','status':'unknown'}"
    Then I process the JsonObject named "myObject" with the RuleProcessor with RuleSet name is "test"
    And the JsonObject named "myObject" is processed and the status is "done"
