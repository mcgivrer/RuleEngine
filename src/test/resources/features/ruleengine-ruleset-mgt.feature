Feature: RuleProcessor Manage RuleSets

  Add or manage RuleSet into the RuleProcessor.

  Background:
    Given a RuleProcessor instance is created
    And I add a RuleSet named "test"
    And I declare a RuleContext to the RuleProcessor

  Scenario: Add a RuleSet
    Given the RuleProcessor instance is up and running
    And I add a new RuleSet named "test2"
    Then the New RuleSet "test2" is listed in RuleProcessor ruleSet list

  Scenario: Retrieve a non existing RuleSet from the RuleProcessor
    Given the RuleProcessor instance is up and running
    And I try to get an "unknown" RuleSet
    Then the UnknownRuleSetException is raised

  Scenario: Remove a RuleSet
    Given the RuleProcessor instance is up and running
    And I remove a RuleSet named "test-ruleset"
    Then the set of Rules "test-ruleset" does not exists anymore
