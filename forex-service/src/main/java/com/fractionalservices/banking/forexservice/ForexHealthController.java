package com.fractionalservices.banking.forexservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexHealthController {
    @GetMapping("/health")
    public String getVersion() {
        return "1.0";
    }

}