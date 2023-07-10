package com.fractionalservices.banking.backend.customerAccount;

import com.fractionalservices.banking.backend.authentication.InvalidCustomerException;
import com.fractionalservices.banking.backend.exception.BadRequestException;
import com.fractionalservices.banking.backend.transaction.NoTransactionException;

public interface CustomerAccountService {
    CustomerAccountDetails getCustomerAccountDetails(String customerId, String currency) throws NoTransactionException, BadRequestException, InvalidCustomerException;
}
