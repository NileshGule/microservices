package com.fractionalservices.banking.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    private static Map<String, List<AccountDetails>> customerAccountMap;

    static {
        initialiseCustomerAccountMap();
    }

    public static void initialiseCustomerAccountMap() {
        log.info("Initialise Customer account-service map");
        customerAccountMap = new HashMap<>();
        String customer1 = "P-0123456789";
        List<AccountDetails> cust1AccountList = new ArrayList<>();
        cust1AccountList.add(new AccountDetails("acct_id_6", customer1, "INR"));
        cust1AccountList.add(new AccountDetails("acct_id_3", customer1, "SGD"));
        cust1AccountList.add(new AccountDetails("acct_id_4", customer1, "USD"));
        customerAccountMap.put(customer1, cust1AccountList);


        String customer2 = "P-0123456790";
        List<AccountDetails> cust2AccountList = new ArrayList<>();
        cust2AccountList.add(new AccountDetails("acct_id_10", "", "USD"));
        cust2AccountList.add(new AccountDetails("acct_id_2", "", "SGD"));
        customerAccountMap.put(customer2, cust2AccountList);

        String customer3 = "P-0123456791";
        List<AccountDetails> cust3AccountList = new ArrayList<>();
        cust3AccountList.add(new AccountDetails("acct_id_9", "", "USD"));
        customerAccountMap.put(customer3, cust3AccountList);

        log.info("Three customers are initialised with id and accounts");
    }

    public AccountDetails getAccountDetailsByCustomer(String customerId, String currency) {

        log.info("Start get Account by CustomerID : {} and Currency: {} ", customerId, currency);

        List<AccountDetails> accountDetails = this.getAccountDetailsByCustomer(customerId);
        if (accountDetails == null || accountDetails.isEmpty()) {
            log.info("No. of Accounts for the customer : {}", customerId);
            return null;
        }
        log.info("No. of Accounts for the customer/currency : {}", accountDetails.size());

        return customerAccountMap.get(customerId).stream()
                .filter(ad -> ad.getAccountCurrency().equalsIgnoreCase(currency))
                .toList().get(0);
    }

    public List<AccountDetails> getAccountDetailsByCustomer(String customerId) {

        log.info("Start get Account by CustomerID : {}", customerId);

        if (customerAccountMap == null) {
            initialiseCustomerAccountMap();
        }

        return customerAccountMap.get(customerId);
    }


}
