package com.fractionalservices.banking.forexservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ForexHealthController {
    @GetMapping("/version")
    public String getVersion() {
        log.info("Get Version for forex");
        return "1.0";
    }

}