Feature: Store - Access to Petstore order

  Store - CRUD with Petstore order

  Scenario Outline:  As a End User, I can control Pet Order Cart
    Given I would like to place an order for a  pet

      | url					| orderId	  | petId	| quantity	| shipdate										| status		|	complete	|
      | /store/order| 1 				| 1			| 1					| 2021-02-10T08:39:11.959Z		| placed		| true			|


    When My order is placed, I would like to get the order information by "<orderid>"
    Then I would like to delete my order by "<orderid>" if I am not not happy with it

    Examples: Valid
      | orderid	|
      | 1				|