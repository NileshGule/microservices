package com.fractionalservices.banking.backend.customerAccountTransaction;

import com.fractionalservices.banking.backend.transaction.TransactionRequest;
import com.fractionalservices.banking.backend.transaction.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface RequestResponseMapper {

    @Mappings({
            @Mapping(target = "txnMonth", source = "month"),
            @Mapping(target = "txnYear", source = "year")
    })
    TransactionRequest toTransactionRequest(CustomerTransactionRequest ctr);

    CustomerTransactionResponse toCustomerTransactionResponse(TransactionResponse tr);
}
