package com.fractionalservices.banking.forexservice;

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
public class RateContoller {
    @GetMapping("/rates")
    public Map<String, Double> getAll() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("forex-data.csv").toURI());

        Stream<String> lines = Files.lines(path);
        Map<String, Double> map = new HashMap<>();
        lines.forEach( line -> {
            line = line.toUpperCase();
            String[] item = line.split(",");
            map.put(item[0] + "/" + item[1], Double.parseDouble(item[2]));
        });

        return map;
    }
}
