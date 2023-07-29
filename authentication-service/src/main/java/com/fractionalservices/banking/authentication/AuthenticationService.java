package com.fractionalservices.banking.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    private static List<AuthenticationDetails> validCustomers = new ArrayList<>();

    public AuthenticationDetails authenticateToken(String token) throws Exception {
        log.info("Start authentication-service customer token");

        if (CollectionUtils.isEmpty(validCustomers)) {
            log.info("Cache is empty, initialising the cache");
            validCustomers = new ArrayList<>();
            validCustomers.add(new AuthenticationDetails("P-0123456789", "employee1@sp.com"));
            validCustomers.add(new AuthenticationDetails("P-0123456790", "employee2@sp.com"));
            validCustomers.add(new AuthenticationDetails("P-0123456791", "employee3@sp.com"));
            log.info("Initialised the cache with size : {} ", validCustomers.size());
        }

        try {
            String emailFromToken = this.getPayloadFromToken(token);
            log.info("Extracted Email from customer token : {}, email : {}", token, emailFromToken);
            AuthenticationDetails accountDetails = validCustomers.stream()
                    .filter(customer -> customer.getEmail().equalsIgnoreCase(emailFromToken))
                    .collect(Collectors.toList())
                    .get(0);

            return accountDetails;
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            throw new Exception("Invalid Customer");
        }
    }


    private String getPayloadFromToken(String input) throws JsonProcessingException {
        log.info("Input from request : {}", input);

        String[] chunks = input.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        log.info("Payload from request : {}", payload);
        ObjectMapper mapper = new ObjectMapper();
        AuthenticationDetails details = mapper.readValue(payload, AuthenticationDetails.class);
        log.info("Auth details retrieved from payload successfully");
        return details.getEmail();
    }
}
