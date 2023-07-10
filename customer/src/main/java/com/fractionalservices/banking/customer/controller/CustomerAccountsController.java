package com.fractionalservices.banking.customer.controller;

import com.fractionalservices.banking.customer.entity.AccountDetails;
import com.fractionalservices.banking.customer.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerAccountsController {

    @Autowired
    private CustomerAccountService customerAccountService;
    @GetMapping("/customer/account/{customerId}/{currency}")
    public AccountDetails getCustomerAccount(@PathVariable String customerId, @PathVariable String currency){
        return customerAccountService.getCustomerAccountDetails(customerId, currency);
    }

    @GetMapping("/customer/account/{customerId}")
    public List<AccountDetails> getCustomerAccount(@PathVariable String customerId){
        return customerAccountService.getCustomerAccountDetails(customerId);
    }

}
