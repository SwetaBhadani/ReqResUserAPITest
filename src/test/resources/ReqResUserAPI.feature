Feature: Test reqres.in API

  Scenario: Verify the creation of a user
    Given I have the endpoint to create a user
    When I hit a request with below details to create a user
      | name     | job    |
      | morpheus | leader |
    Then I verify the creation of user from the response
    And I verify the status code is equal to 200

  Scenario: Verify creating a user with invalid request body
    Given I have the endpoint to create a user
    When I hit the endpoint with invalid request body
    Then I get an error with 400 status code
