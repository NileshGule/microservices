package com.fractionalservices.banking.backend.authentication;

import com.fractionalservices.banking.backend.exception.BadRequestException;
import org.springframework.http.HttpHeaders;

public interface AuthenticationValidationService {
    CustomerDetails validateAndGetCustomer(HttpHeaders headers, String customerId) throws BadRequestException, InvalidCustomerException;
}
