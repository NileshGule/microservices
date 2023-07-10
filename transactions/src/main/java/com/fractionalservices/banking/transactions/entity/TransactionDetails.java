package com.fractionalservices.banking.transactions.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDetails {
    private String transactionId;
    private Double amount;
    private String currency;
    private String acctIBank;
    private LocalDate valueDate;
    private String description;

    public TransactionDetails(String transactionId, Double amount, String currency, String acctIBank,
                              LocalDate valueDate, String description) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.acctIBank = acctIBank;
        this.valueDate = valueDate;
        this.description = description;
    }
}
