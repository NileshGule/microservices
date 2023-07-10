package com.fractionalservices.banking.transactions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    @GetMapping("/txn/health")
    public String getVersion() {
        return "1.0";
    }

}