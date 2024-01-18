package com.fractionalservices.banking.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AccountsController {

    private static final Logger log = LoggerFactory.getLogger(AccountsController.class);

    // convert this to a constructor injection
    private AccountService customerAccountService;

    public AccountsController(AccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    @GetMapping("/{customerId}/{currency}")
    public AccountDetails getAccountDetailsByCustomerId(@PathVariable @NonNull String customerId, @PathVariable @NonNull String currency) {
        log.info("Start get Account by customer Id : {}, currency {}", customerId, currency);


        return customerAccountService.getAccountDetailsByCustomer(customerId, currency);
    }

    @GetMapping("/{customerId}")
    public List<AccountDetails> getAccountDetailsByCustomerId(@PathVariable @NonNull String customerId) {
        log.info("Start get Account(s) by customer Id : {}", customerId);
        return customerAccountService.getAccountDetailsByCustomer(customerId);
    }

}
