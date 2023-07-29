package com.fractionalservices.banking.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionController.class);

    @GetMapping("/version")
    public String getVersion() {
        LOGGER.info("Checking health status ");
        return "1.0";
    }

}