package com.fractionalservices.banking.sso.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fractionalservices.banking.sso.entity.CustomerDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerAuthenticationService {


    private static List<CustomerDetails> validCustomers = new ArrayList<>();


    public CustomerDetails authenticateToken(String token) throws Exception {

        if (CollectionUtils.isEmpty(validCustomers)) {
            validCustomers = new ArrayList<>();
            validCustomers.add(new CustomerDetails("P-0123456789", "employee1@sp.com"));
            validCustomers.add(new CustomerDetails("P-0123456790", "employee2@sp.com"));
            validCustomers.add(new CustomerDetails("P-0123456791", "employee3@sp.com"));
        }

        try {
            String customerEmailFromToken = this.getPayloadFromToken(token);
            CustomerDetails customerDetails = validCustomers.stream()
                    .filter(customer -> customer.getEmail().equalsIgnoreCase(customerEmailFromToken))
                    .collect(Collectors.toList())
                    .get(0);

            return customerDetails;
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            throw new Exception("Invalid Customer");
        }
    }


    private String getPayloadFromToken(String input) throws JsonProcessingException {

        String[] chunks = input.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        System.out.println(payload);
        ObjectMapper mapper = new ObjectMapper();
        CustomerDetails details = mapper.readValue(payload, CustomerDetails.class);
        return details.getEmail();
    }
}
