Feature: Using a Rule with multiple contextual values

  Test the RuleContext capabilities.

  Scenario: Define Contextual data element
    Given the RuleEngine is up and running with maxPoolSize="2"
    And I put "{'id':1000,'field-name':'height','comparator':'greaterOrEqual','threshold':150,'tag-marker':'height-normal'}" in the RuleContext "TagHeightOnThreshold"
    And I put "{'tags-attribute':'tags'}" in the RuleContext "common"
    And I create a new RuleProcessor
    And I add a RuleSet named "test-context"
    And I add TagWithThresholdOnIntRule to the RuleSet "test-context"
    And I create a new JsonObject data with "{'id':999,'name':'myObject','age':25, 'weight':78,'height':170,'date':'2018-09-13 12:00:00.00T000','status':'unknown'}"
    Then Submit the RuleProcessor to the RuleEngine with RuleSet "test-context"
    And The JsonObject identified by "999" has been processed by RuleEngine and the "tags" is "height-normal"
