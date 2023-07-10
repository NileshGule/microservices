package com.fractionalservices.banking.transactions.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class TransactionRequest {

    private String acctIban;
    private Integer txnMonth;
    private Integer txnYear;
    private Integer startPage;
    private Integer pageSize;
}
