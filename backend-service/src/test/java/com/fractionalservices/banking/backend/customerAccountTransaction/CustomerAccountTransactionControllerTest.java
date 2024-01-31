package com.fractionalservices.banking.backend.customerAccountTransaction;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fractionalservices.banking.backend.authentication.AuthenticationValidationService;
import com.fractionalservices.banking.backend.authentication.CustomerDetails;
import com.fractionalservices.banking.backend.authentication.InvalidCustomerException;
import com.fractionalservices.banking.backend.exception.BadRequestException;
import com.fractionalservices.banking.backend.transaction.NoTransactionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

class CustomerAccountTransactionControllerTest {

    @Mock
    private AuthenticationValidationService authenticationValidationService;

    @Mock
    private CustomerAccountTransactionService customerAccountTransactionService;

    @InjectMocks
    private CustomerAccountTransactionController customerAccountTransactionController;

    @Test
    void testGetCustomerTransactions() throws BadRequestException, InvalidCustomerException, NoTransactionException {
        // Arrange
        CustomerTransactionRequest request = new CustomerTransactionRequest();
        HttpHeaders headers = new HttpHeaders();
        CustomerDetails customerDetails = new CustomerDetails();
        List<CustomerTransactionResponse> responses = new ArrayList<>();
        CustomerAccountTransactionResponse expectedResponse = new CustomerAccountTransactionResponse();

        when(authenticationValidationService.validateAndGetCustomer(headers, request.getCustomerId())).thenReturn(customerDetails);
        when(customerAccountTransactionService.getCustomerTransactions(request, headers)).thenReturn(responses);
        when(customerAccountTransactionController.getTransactionTotal(responses)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<CustomerAccountTransactionResponse> responseEntity = customerAccountTransactionController.getCustomerTransactions(request, headers);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}