package com.fractionalservices.banking.backend.transaction;

import java.util.List;

public interface TransactionService {
    List<TransactionResponse> getTransactionAccounts(TransactionRequest transactionRequest);
}
