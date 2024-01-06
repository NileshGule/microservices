Feature: MyFeature: I want to validate if customer accessing the system are valid customer

  Scenario: If there is attempt to bypass the authentication without any auth headers, system should return validation error
    When A customer is trying to access transactions without proper authentication
    Then System should fail the request
    And "Invalid authentication details" message is shown

  Scenario Outline: If there is attempt to bypass the authentication with wrong auth headers, system should return validation error
    When A customer is trying to access transactions without proper authentication
    And Authentication Header is <wrong_auth_header>
    Then System should fail the request
    And "Invalid authentication details" message is shown
    Examples:
      |  wrong_auth_header |
      | null         |
      | " "          |
      | ""           |

  Scenario: If there is valid token but customer id is not valid, system should return valiation error
    When A customer has valid authentication header
    And customerId is invalid
    Then System should fail the request
    And "Invalid Customer" message is shown

  Scenario: If there is invalid token, system should return validation error
    When A customer has invalid authentication header
    Then System should fail the request
    And "Invalid Customer" message is shown

  Scenario: If there is token and customer id, system should authenticate customer
    When A customer has valid authentication header
    And customerId is valid
    Then System should authenticate the request