package com.fractionalservices.banking.backend.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private String acctNumber;
    private Integer txnMonth;
    private Integer txnYear;
    private Integer pageNo;
    private Integer pageSize;
}
