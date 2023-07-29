package com.fractionalservices.banking.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    private AuthenticationService customerAuthenticationService;

    @GetMapping("/authenticate/token")
    public AuthenticationDetails authenticateCustomer(@RequestParam String token) throws Exception {
        log.info("Start authentication for token : {}", token);
        return customerAuthenticationService.authenticateToken(token);
    }
}
