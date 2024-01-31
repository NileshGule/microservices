package com.fractionalservices.banking.backend.customerAccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fractionalservices.banking.backend.exception.BadRequestException;
import com.fractionalservices.banking.backend.transaction.NoTransactionException;

/**
 * This class implements the CustomerAccountService interface and provides the implementation for retrieving customer account details.
 */
@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private static Logger logger = LoggerFactory.getLogger(CustomerAccountServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${services.url.account-service-customer-currency}")
    private @NonNull String customerAccountServiceCustomerCurrencyUrl;

    @Override
    public CustomerAccountDetails getCustomerAccountDetails(String customerId, String currency)
            throws NoTransactionException, BadRequestException {

        logger.debug("Get customer Account details : start");
        validateRequest(customerId, currency);

        try {
            logger.info("Get customer details from account_service");
            ResponseEntity<CustomerAccountDetails> responseEntity = restTemplate.exchange(
                    customerAccountServiceCustomerCurrencyUrl,
                    HttpMethod.GET, null, CustomerAccountDetails.class, customerId, currency);
            logger.debug("Get customer Account details : ends");
            CustomerAccountDetails customerAccountDetails = responseEntity.getBody();
            if (customerAccountDetails == null) {
                logger.info("No accounts found for the customer.");
                throw new CustomerAccountException("No accounts found for the customer ");
            }
            customerAccountDetails.setCustomerId(customerId);
            logger.info("Call to get customer accounts is completed");

            logger.info("Get customer transaction - ends");
            return customerAccountDetails;
        } catch (Exception ex) {
            logger.error("No customer accounts found with customerId : {}, currency : {}", customerId, currency);
            logger.error("Error : Get customer details from customer_account_service", ex);
            throw new NoTransactionException("No customer accounts found with customerI");
        }
    }

    public int addition(int a, int b) {
        return a + b;
    }

    protected void validateRequest(String customerId, String currency) throws BadRequestException {
        if (currency == null || currency.trim().length() == 0) {
            logger.error("No currency provided error");
            throw new BadRequestException("Currency is not provided");
        }

        if (customerId == null || customerId.trim().length() == 0) {
            logger.error("Invalid customer");
            throw new BadRequestException("CustomerId is not provided");
        }
    }
}
