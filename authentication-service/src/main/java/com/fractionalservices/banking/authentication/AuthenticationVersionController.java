package com.fractionalservices.banking.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationVersionController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationVersionController.class);
    @GetMapping("/version")
    public String getVersion() {
        log.info("Get version from Authentication service");
        return "1.0";
    }

}