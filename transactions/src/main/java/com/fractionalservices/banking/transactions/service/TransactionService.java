package com.fractionalservices.banking.transactions.service;

import com.fractionalservices.banking.transactions.entity.TransactionDetails;

import java.util.List;

public interface TransactionService {

    List<TransactionDetails> getAccountTransactions(String acctNumber, Integer txnYear, Integer txnMonth, Integer pageSize, Integer startPage);
}
