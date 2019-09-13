Feature: RuleProcessor Manage Rules

  Add or manage Rule into the RuleProcessor.

  Scenario: Add a Rule to the RuleProcessor
    Given the RuleProcessor instance is up and running
    And I add a new Rule
    Then the New Rule is listed in RuleProcessor rules list

  Scenario: Retrieve a non existing Rule from the RuleProcessor
    Given the RuleProcessor instance is up and running
    And I try to get an unknown Role
    Then the UnknownRuleException is raised

  Scenario: Remove a Rule
    Given the RuleProcessor instance is up and running
    And I remove a Rule named "test-rule"
    Then the "test-rule" Rule does not exists anymore
