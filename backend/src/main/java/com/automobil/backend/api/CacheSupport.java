package com.automobil.backend.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CacheSupport {
    private static final Logger LOGGER = LogManager.getLogger(CacheSupport.class.getName());
    private final CacheManager cacheCongig;

    public CacheSupport(CacheManager cacheCongig) {
        this.cacheCongig = cacheCongig;
    }

    @PreDestroy
    public void saveCache() throws IOException {
        FileWriter fileWriter = new FileWriter("C:\\Users\\Artem\\Desktop\\cache.txt", false);
        fileWriter.write(getCache());
        fileWriter.close();
        LOGGER.info("in PreDestroy");
    }

    @PostConstruct
    public void loadingCache() throws IOException {
        LOGGER.info("in postconstruct");
        Cache invoices = (Cache) cacheCongig.getCache("vin");
        String text = new String(Files.readAllBytes(Paths.get("C:\\Users\\Artem\\Desktop\\cache.txt")), StandardCharsets.UTF_8);
        String json = text;
        String tmp = "";
//        Map<String, String> map = new HashMap<>();
        int start = 0;
        int end = 0;
        while (!json.isEmpty()) {
            start = json.indexOf("customvin") + 10;
            json = json.substring(start);

            end = json.indexOf("customvin") - 1;
            if (end > 0) {
                tmp = json.substring(0, end);
                invoices.put(tmp.substring(0, 17), tmp.substring(17));
                json = json.substring(end);
            } else {
                invoices.put(json.substring(0, 17), json.substring(17));
                json = "";
            }
        }
    }

    public String getCache() {
        StringBuilder cache = new StringBuilder();
        Cache invoices = (Cache) cacheCongig.getCache("vin");
        List<String> auth = new ArrayList<>();
        Map<String, String> map = (Map<String, String>) invoices.getNativeCache();
        map.forEach((key, value) -> cache.append("{customvin:").append(key).append(value));
//        System.out.println(cache.toString());
        return cache.toString();
    }


}
