package com.fractionalservices.banking.customer.service;

import com.fractionalservices.banking.customer.entity.AccountDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerAccountService {

    private static Map<String, List<AccountDetails>> customerAccountMap;

    static {
        initialiseCustomerAccointMap();
    }

    public static void initialiseCustomerAccointMap(){
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
    }

    public AccountDetails getCustomerAccountDetails(String customerId, String currency) {

        List<AccountDetails> accountDetails = this.getCustomerAccountDetails(customerId);

        return
                customerAccountMap.get(customerId).stream()
                        .filter(ad -> {
                            boolean ism = ad.getAccountCurrency().equalsIgnoreCase(currency);
                            return ism;
                        })
                        .collect(Collectors.toList()).get(0);
    }

    public List<AccountDetails> getCustomerAccountDetails(String customerId) {

        if (customerAccountMap == null) {
            initialiseCustomerAccointMap();
        }

        return customerAccountMap.get(customerId);
    }


}
