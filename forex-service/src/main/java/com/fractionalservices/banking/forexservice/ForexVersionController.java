package com.fractionalservices.banking.forexservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexVersionController {

    private static final Logger log = LoggerFactory.getLogger(ForexVersionController.class);
    @GetMapping("/version")
    public String getVersion() {
        log.info("Get Version for forex");
        return "1.0";
    }

}