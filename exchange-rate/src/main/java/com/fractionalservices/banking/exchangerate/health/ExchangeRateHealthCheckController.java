package com.fractionalservices.banking.exchangerate.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateHealthCheckController {
    @GetMapping("/exchange-rate/health")
    public String getVersion() {
        return "1.0";
    }

}