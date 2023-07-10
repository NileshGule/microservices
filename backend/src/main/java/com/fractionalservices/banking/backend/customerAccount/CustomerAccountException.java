package com.fractionalservices.banking.backend.customerAccount;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Account Found")
public class CustomerAccountException extends Exception {
    public CustomerAccountException(String message) {
        super(message);
    }
}
