Feature: Test reqres.in API

  Background:
    Given As a user, I have a valid endpoint

  Scenario: Verify the creation of a user
    When I hit a request with below details to create a user
      | name     | job    |
      | morpheus | leader |
    Then I verify the creation of user from the response
    And I verify the status code is equal to 201

  Scenario: Verify creating a user with invalid request body
    When I hit the endpoint with invalid request body
    Then I get an error with 400 status code


  Scenario: Verify retrieving all users with default per page query param
    When I hit a GET request
    Then I get the success response with status code 200
    And I validate number of users returned is equal to per page value
