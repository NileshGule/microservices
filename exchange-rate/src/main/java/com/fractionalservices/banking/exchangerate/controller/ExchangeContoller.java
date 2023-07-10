package com.fractionalservices.banking.exchangerate.controller;

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
public class ExchangeContoller {
    @GetMapping("/exchange-rate/getAll")
    public Map<String, Double> getAll() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("exchange-rate-data.csv").toURI());

        Stream<String> lines = Files.lines(path);
        Map<String, Double> map = new HashMap<>();
        lines.forEach( line -> {
            line = line.toUpperCase();
            String[] item = line.split(",");
            map.put(item[0] + "/" + item[1], new Double(item[2]));
        });

        return map;
    }
}
