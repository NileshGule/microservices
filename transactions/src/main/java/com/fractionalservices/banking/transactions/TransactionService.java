package com.fractionalservices.banking.transactions;

import com.fractionalservices.banking.transactions.TransactionDetails;

import java.util.List;

public interface TransactionService {

    List<TransactionDetails> getAccountTransactions(String acctNumber, Integer txnYear, Integer txnMonth, Integer pageSize, Integer startPage);
}
