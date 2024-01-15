package com.fractionalservices.banking.backend.customerAccount;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CustomerAccountStepDefinitionsTests {

    @Autowired
    private CustomerAccountServiceImpl service;
    private String customerId;
    private String currency;
    private CustomerAccountDetails accountDetails;
    private Exception exception;

    @Given("I am a customer with customer_id as {string}")
    public void i_am_a_customer_with_customer_id_as(String customerId) {
        this.customerId = customerId;
    }

    @Given("My selected currency is {string}")
    public void my_selected_currency_is(String currency) {
        if (currency == null || currency.trim().length() == 0) {
            this.currency = null;
            return;
        }
        this.currency = currency;
    }

    @When("I request for my accounts")
    public void i_request_for_my_accounts() {
        try {
            accountDetails = service.getCustomerAccountDetails(customerId, currency);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("{string} exception message is shown to me")
    public void exception_message_is_shown_to_me(String message) {
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Then("My accounts are returned")
    public void my_accounts_are_returned() {
        assertNotNull(accountDetails);

    }

    @And("Currency returned is {string}")
    public void currencyReturnedIs(String currency) {
        assertEquals(currency, accountDetails.getAccountCurrency());
    }

    @And("CustomerId returned is {string}")
    public void customerIdReturnedIs(String accountId) {

        assertEquals(customerId, accountDetails.getCustomerId());
    }

    @And("Account_Number returned is {string}")
    public void account_numberReturnedIs(String accountNo) {
        assertEquals(accountNo, accountDetails.getAccountId());
    }
}