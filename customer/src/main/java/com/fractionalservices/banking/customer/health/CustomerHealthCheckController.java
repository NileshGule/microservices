package com.fractionalservices.banking.customer.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerHealthCheckController {
    @GetMapping("/customer/version")
    public String getVersion() {
        return "1.0";
    }

}