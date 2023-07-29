package com.fractionalservices.banking.forexservice;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@RestController

public class RateController {

    private static final Logger log = LoggerFactory.getLogger(RateController.class);

    @GetMapping("/rates")
    public Map<String, Double> getAll() throws IOException, URISyntaxException {
        log.info("Start get forex rates");
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("forex-data.csv").toURI());

        Stream<String> lines = Files.lines(path);
        Map<String, Double> map = new HashMap<>();
        lines.forEach( line -> {
            line = line.toUpperCase();
            String[] item = line.split(",");
            map.put(item[0] + "/" + item[1], Double.parseDouble(item[2]));
        });
        log.info("Returning data from forex with combination of {} currencies:", map.size());

        return map;
    }
}
