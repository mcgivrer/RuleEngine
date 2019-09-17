Feature: RuleProcessor Manage RuleSets

  Add or manage RuleSet into the RuleProcessor.

  @tag: RuleEngine
  Scenario: Add a RuleSet
    Given the RuleProcessor instance is up and running
    And I add a new RuleSet
    Then the New RuleSet is listed in RuleProcessor ruleSet list

  @tag: RuleEngine
  Scenario: Retrieve a non existing RuleSet from the RuleProcessor
    Given the RuleProcessor instance is up and running
    And I try to get an "unknown" RuleSet
    Then the UnknownRuleSetException is raised

  @tag: RuleEngine
  Scenario: Remove a RuleSet
    Given the RuleProcessor instance is up and running
    And I remove a RuleSet named "test-ruleset"
    Then the set of Rules "test-ruleset" does not exists anymore
