package com.fractionalservices.banking.backend.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class TransactionResponse {
    private String transactionId;
    private Double amount;
    private String currency;
    private String acctIBank;
    private LocalDate valueDate;
    private String description;

    public TransactionResponse(String transactionId, Double amount, String currency, String acctIBank,
                               LocalDate valueDate, String description) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.acctIBank = acctIBank;
        this.valueDate = valueDate;
        this.description = description;
    }
}
