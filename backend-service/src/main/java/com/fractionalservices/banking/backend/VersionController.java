package com.fractionalservices.banking.backend;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionController.class);

    @Autowired
    private ObservationRegistry observationRegistry;

    @GetMapping("/version")
    public String getVersion() {
        LOGGER.info("Checking health status ");
        return Observation.createNotStarted("getVersion", observationRegistry).observe(() -> "1.0");
    }

}