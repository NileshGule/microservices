package com.fractionalservices.banking.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionServiceImpl service;

    @PostMapping("/txn/accountTransactions")
    public List<TransactionDetails> getAccountTransactions(@RequestBody TransactionRequest t) {
        return service.getAccountTransactions(t.getAcctNumber(), t.getTxnYear(), t.getTxnMonth(), t.getPageSize(), t.getStartPage());
    }
}
