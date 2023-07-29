package com.fractionalservices.banking.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountsController {

    @Autowired
    private AccountService customerAccountService;
    @GetMapping("/{customerId}/{currency}")
    public AccountDetails getCustomerAccount(@PathVariable String customerId, @PathVariable String currency){
        return customerAccountService.getCustomerAccountDetails(customerId, currency);
    }

    @GetMapping("/{customerId}")
    public List<AccountDetails> getCustomerAccount(@PathVariable String customerId){
        return customerAccountService.getCustomerAccountDetails(customerId);
    }

}
