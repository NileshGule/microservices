package com.fractionalservices.banking.backend.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fractionalservices.banking.backend.customerAccount.CustomerAccountException;
import com.fractionalservices.banking.backend.customerAccountTransaction.CustomerAccountTransactionController;
import com.fractionalservices.banking.backend.customerAccountTransaction.CustomerAccountTransactionResponse;
import com.fractionalservices.banking.backend.customerAccountTransaction.CustomerAccountTransactionService;
import com.fractionalservices.banking.backend.customerAccountTransaction.CustomerTransactionRequest;
import com.fractionalservices.banking.backend.customerAccountTransaction.CustomerTransactionResponse;
import com.fractionalservices.banking.backend.exception.ApiError;
import com.fractionalservices.banking.backend.exception.BadRequestException;
import com.fractionalservices.banking.backend.exception.ForbiddenException;
import com.fractionalservices.banking.backend.transaction.NoTransactionException;
import com.fractionalservices.banking.backend.utils.MockRestApiUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.AccountException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


// @RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AuthenticationFeatureIntegrationTest {

    private String exceptionMessage;

    @InjectMocks
    @Autowired
    private CustomerAccountTransactionController controller;

    @Mock
    private CustomerAccountTransactionService customerAccountTransactionService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${services.url.authenticate-service}")
    private String authenticateUrl;

    private static String validAuthenticationHeader = "P-0123456789eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyAiY3VzdG9tZXJJZCI6ICJQLTAxMjM0NTY3ODkiICwgImVtYWlsIjoiZW1wbG95ZWUzQHNwLmNvbSJ9.nLxacDiNrEOcvZ-qlGgJ1ugEGNBxTck2AwFBIwZBsS0";

    private static String validCustomerId = "P-0123456791";
    private HttpHeaders headers;
    private String customerId;

    @When("A customer is trying to access transactions without proper authentication")
    public void when_customer_login_without_auth_header() {
        // No Authentication headers are set
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @When("A customer has invalid authentication header")
    public void when_customer_has_invalid_auth_token() {
        // No Authentication headers are set
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("authorization", "invalid_auth_token");
    }

    @When("A customer has valid authentication header")
    public void when_customer_has_auth_header() {
        // No Authentication headers are set
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("authorization", validAuthenticationHeader);
    }

    @When("Authentication Header is null")
    public void authentication_header_is_null() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("authorization", null);
    }

    @When("Authentication Header is {string}")
    public void authentication_header_is(String string) {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("authorization", string);
    }

    @When("customerId is invalid")
    public void when_customer_id_is_invalid() {
        this.customerId = "invalid_customerId";
    }

    @When("customerId is valid")
    public void when_customer_id_is_valid() {
        this.customerId = validCustomerId;
    }

    @Then("System should fail the request")
    public void system_fails_the_request() throws URISyntaxException, JsonProcessingException {

        // Mock - Authentication Rest API Call
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, "Invalid Request", "No authentication headers");
        MockRestApiUtils.authenticationApiCall(authenticateUrl, restTemplate, HttpStatus.NOT_FOUND, error);

        // Actual Call
        exceptionMessage = Assertions.assertThrows(Exception.class, () -> {
            controller.getCustomerTransactions(new CustomerTransactionRequest(), headers);
        }).getMessage();
    }

    @Then("{string} message is shown")
    public void message_is_shown(String string) {
        Assertions.assertEquals(this.exceptionMessage, string);
    }

    @Then("System should authenticate the request")
    public void system_authenticate_the_request() throws URISyntaxException, JsonProcessingException, NoTransactionException, AccountException, ForbiddenException, BadRequestException, InvalidCustomerException, CustomerAccountException {

        // Mock - Authentication Rest API Call
        CustomerDetails customerDetails = new CustomerDetails(validCustomerId);
        MockRestApiUtils.authenticationApiCall(authenticateUrl, restTemplate, HttpStatus.OK, customerDetails);

        //Mock Service method call
        List<CustomerTransactionResponse> responses = populateCustomerTransactionResponse();
        MockitoAnnotations.initMocks(this);
        Mockito.when(customerAccountTransactionService.getCustomerTransactions(
                        any(CustomerTransactionRequest.class), any(HttpHeaders.class)))
                .thenReturn(responses);

        // Actual Call
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", validAuthenticationHeader);
        CustomerTransactionRequest ctr = populateCustomerTransactionRequest();
        ResponseEntity<CustomerAccountTransactionResponse> responseEntity = controller.getCustomerTransactions(ctr, headers);

        // Assertions
        CustomerAccountTransactionResponse catr = responseEntity.getBody();
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(100d, catr.getDebitTotal());
    }

    @NotNull
    private CustomerTransactionRequest populateCustomerTransactionRequest() {
        CustomerTransactionRequest ctr = new CustomerTransactionRequest();
        ctr.setCustomerId(this.customerId);
        ctr.setCurrency("INR");
        ctr.setYear(2020);
        ctr.setMonth(10);
        return ctr;
    }

    private List<CustomerTransactionResponse> populateCustomerTransactionResponse() {
        List<CustomerTransactionResponse> responses = new ArrayList<>();
        CustomerTransactionResponse response = new CustomerTransactionResponse();
        response.setTransactionType("Debit");
        response.setAmount(100d);
        responses.add(response);

        return responses;
    }
}
