Feature: Store - Access to Petstore order

  Store - Check Inventory status

  Scenario Outline:  As a Store Owner, I would like to check my inventory (Positive Scenario)
    Given I hit the "<url>"
    When Request should submit and Positive API response should received "<response>"
    Then Inventory data should display

    Examples: Valid
      | url								| response	|
      | /store/inventory	| 200				|

  Scenario Outline:  As a Store Owner, I would like to check my inventory (Negative Scenario)
    Given I hit the "<url>"
    When Request should submit and Positive API response should received "<response>"
    Then Inventory data should display

    Examples: Invalid
      | url								| response	|
      | /store/inventry 	| 404				|

