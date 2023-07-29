package com.fractionalservices.banking.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AccountsController {

    private static final Logger log = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    private AccountService customerAccountService;
    @GetMapping("/{customerId}/{currency}")
    public AccountDetails getCustomerAccount(@PathVariable String customerId, @PathVariable String currency){
        log.info("Start get Account by customer Id : {}, currency {}", customerId, currency);
        return customerAccountService.getAccountDetailsByCustomer(customerId, currency);
    }

    @GetMapping("/{customerId}")
    public List<AccountDetails> getCustomerAccount(@PathVariable String customerId){
        log.info("Start get Account(s) by customer Id : {}", customerId);
        return customerAccountService.getAccountDetailsByCustomer(customerId);
    }

}
