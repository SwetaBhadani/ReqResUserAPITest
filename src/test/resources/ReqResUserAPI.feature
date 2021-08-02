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
    Then I verify the status code is equal to 200
    And I validate number of users returned is equal to per page value

  Scenario: Verify retrieving single user with id
    When I hit a GET request with user id 1
    Then I verify the status code is equal to 200
    And I validate the user details for user id 1 is same as below

      | id | email                  | first_name | last_name |
      | 1  | george.bluth@reqres.in | George     | Bluth     |


  Scenario: Verify retrieving single user with invalid user id
    When I hit a GET request with user id 0
    Then I verify the status code is equal to 404