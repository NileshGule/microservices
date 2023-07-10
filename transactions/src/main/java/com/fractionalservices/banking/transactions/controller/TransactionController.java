package com.fractionalservices.banking.transactions.controller;

import com.fractionalservices.banking.transactions.entity.TransactionDetails;
import com.fractionalservices.banking.transactions.entity.TransactionRequest;
import com.fractionalservices.banking.transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/txn/accountTransactions")
    public List<TransactionDetails> getAccountTransactions(@RequestBody TransactionRequest t) {
        return service.getAccountTransactions(t.getAcctIban(), t.getTxnYear(), t.getTxnMonth(), t.getPageSize(), t.getStartPage());
    }
}
