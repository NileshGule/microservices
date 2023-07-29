package com.fractionalservices.banking.backend.customerAccountTransaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountTransactionResponse {

    private Double creditTotal;
    private Double debitTotal;
    private List<CustomerTransactionResponse> customerTransactionResponses;

}
