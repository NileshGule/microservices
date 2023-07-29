package com.fractionalservices.banking.backend.customerAccountTransaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class CustomerTransactionResponse {

    private String transactionId;
    private Double amount;
    private String currency;
    private String acctNumber;
    private LocalDate valueDate;
    private String description;
    private String transactionType;

    public CustomerTransactionResponse(String transactionId, Double amount, String currency, String acctNumber,
                                       LocalDate valueDate, String description) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.acctNumber = acctNumber;
        this.valueDate = valueDate;
        this.description = description;
    }
}
