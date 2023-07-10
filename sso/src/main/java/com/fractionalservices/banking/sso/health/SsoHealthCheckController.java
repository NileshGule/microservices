package com.fractionalservices.banking.sso.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SsoHealthCheckController {
    @GetMapping("/sso/health")
    public String getVersion() {
        return "1.0";
    }

}