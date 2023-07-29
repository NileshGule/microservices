package com.fractionalservices.banking.backend.customerAccountTransaction;


import com.fractionalservices.banking.backend.authentication.InvalidCustomerException;
import com.fractionalservices.banking.backend.customerAccount.CustomerAccountDetails;
import com.fractionalservices.banking.backend.customerAccount.CustomerAccountService;
import com.fractionalservices.banking.backend.customerAccount.CustomerAccountServiceImpl;
import com.fractionalservices.banking.backend.exception.BadRequestException;
import com.fractionalservices.banking.backend.transaction.NoTransactionException;
import com.fractionalservices.banking.backend.transaction.TransactionRequest;
import com.fractionalservices.banking.backend.transaction.TransactionResponse;
import com.fractionalservices.banking.backend.transaction.TransactionService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerAccountTransactionServiceImpl implements CustomerAccountTransactionService {

    private static Logger log = LoggerFactory.getLogger(CustomerAccountTransactionServiceImpl.class);

    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private RestTemplate restTemplate;

    private RequestResponseMapper requestResponseMapper = Mappers.getMapper(RequestResponseMapper.class);

    @Override
    public List<CustomerTransactionResponse> getCustomerTransactions(CustomerTransactionRequest customerTransactionRequest, HttpHeaders headers) throws NoTransactionException, BadRequestException, InvalidCustomerException {
        String customerId = customerTransactionRequest.getCustomerId();
        String currency = customerTransactionRequest.getCurrency();
        log.info("Start getCustomerTransactions : CustomerID :{}, Currency: {} ", customerId, currency);

        CustomerAccountDetails customerAccountDetails = customerAccountService.getCustomerAccountDetails(customerId, currency);
        customerTransactionRequest.setAcctNumber(customerAccountDetails.getAccountId());
        log.info("Retrieved customer account details successfully : AccountNumber : {}", customerAccountDetails.getAccountId());
        return getAccountTransactions(customerTransactionRequest);
    }

    private List<CustomerTransactionResponse> getAccountTransactions(CustomerTransactionRequest customerTransactionRequest) {
        log.info("Start mapping the requests using mappers");
        TransactionRequest transactionRequest = requestResponseMapper.toTransactionRequest(customerTransactionRequest);
        List<TransactionResponse> transactionResponse = transactionService.getTransactions(transactionRequest);

        List<CustomerTransactionResponse> customerTransactionResponses = new ArrayList<>();
        transactionResponse.forEach(tr -> {
            CustomerTransactionResponse ctr = requestResponseMapper.toCustomerTransactionResponse(tr);
            String transactionType = ctr.getAmount() > 0 ? "Credit" : "Debit";
            ctr.setTransactionType(transactionType);
            customerTransactionResponses.add(ctr);
        });

        return customerTransactionResponses;
    }

}
