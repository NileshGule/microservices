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
    private String acctNumber;
    private LocalDate valueDate;
    private String description;

    public TransactionResponse(String transactionId, Double amount, String currency, String acctNumber,
                               LocalDate valueDate, String description) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.acctNumber = acctNumber;
        this.valueDate = valueDate;
        this.description = description;
    }
}
