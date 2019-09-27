Feature: RuleProcessor Manage Rules

  Add or manage Rule into the RuleProcessor.

  Background:
    Given a RuleProcessor instance is created
    And I add a RuleSet named "test"
    And I declare a RuleContext to the RuleProcessor

  Scenario: Add a Rule in a RuleSet to the RuleProcessor
    Given the RuleProcessor instance is up and running
    And I add a new Rule named "myRule" to RuleSet "test"
    Then the New Rule "myRule" is listed in RuleProcessor rules list

  Scenario: Retrieve a non existing Rule from the RuleProcessor
    Given the RuleProcessor instance is up and running
    And I try to get an "unknown" Rule from the RuleSet "test"
    Then the UnknownRuleException is raised

  Scenario: Remove a Rule in a RuleSet from RuleProcessor
    Given the RuleProcessor instance is up and running
    And I remove a Rule named "test-rule" from RuleSet "test"
    Then the "test-rule" Rule does not exists anymore
