Feature: As a validated customer system should be able to provide the account(s) I have with the bank

  Scenario: As a customer, if currency is not provided, account is Not returned
    Given I am a customer with customer_id as "123"
          And My selected currency is ""
    When  I request for my accounts
    Then "Currency is not provided" exception message is shown to me

  Scenario: As a customer, if customerId is not provided, account is Not returned
    Given I am a customer with customer_id as ""
    And My selected currency is "USD"
    When  I request for my accounts
    Then "CustomerId is not provided" exception message is shown to me

  Scenario: As a customer, if customerId provided is invalid, account is Not returned
    Given I am a customer with customer_id as "123"
    And My selected currency is "USD"
    When  I request for my accounts
    Then "No customer accounts found with customerI" exception message is shown to me

  Scenario: As a customer, if currency provided is invalid, account is Not returned
    Given I am a customer with customer_id as "P-0123456791"
    And My selected currency is "USDDD"
    When  I request for my accounts
    Then "No customer accounts found with customerI" exception message is shown to me


  Scenario: As a customer, if currency is provided, my account is returned
    Given I am a customer with customer_id as "P-0123456791"
          And My selected currency is "USD"
    When  I request for my accounts
    Then My accounts are returned
    And Currency returned is "USD"
    And CustomerId returned is "P-0123456791"
    And Account_Number returned is "acct_id_9"