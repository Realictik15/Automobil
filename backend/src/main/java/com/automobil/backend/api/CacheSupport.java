package com.automobil.backend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CacheSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportApi.class);
    private final CacheManager cacheCongig;

    public CacheSupport(CacheManager cacheCongig) {
        this.cacheCongig = cacheCongig;
    }

    @PreDestroy
    public void saveCache() throws IOException {
        LOGGER.info("in predestroy");
        FileWriter fileWriter = new FileWriter("C:\\Users\\Artem\\Desktop\\cache.txt",false);
        fileWriter.write(getCache());
        fileWriter.close();
    }

//    @PostConstruct
//    public void loadingCache() {
////        Cache invoices = (Cache) cacheCongig.getCache("vin");
////        invoices.put();
//    }

    public String getCache() {
        StringBuilder cache = new StringBuilder();
        Cache invoices = (Cache) cacheCongig.getCache("vin");
        List<String> auth = new ArrayList<>();
        Map<String, String> map = (Map<String, String>) invoices.getNativeCache();
        System.out.println(map.toString());
        return map.toString();
    }


}
