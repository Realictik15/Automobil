package com.automobil.backend.api;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class ReportApi {
    private static final Logger LOGGER = LogManager.getLogger(ReportApi.class.getName());

    private static final String GIBDD_TOKEN = "e62f5ccd-273a-438b-944f-a9b8475ee25d";
    private static final String GIBDD_API_BASE_URL = "https://ygibdd.ru/parserapi/v1/gibdd/";
    private static final String OMDB_MIME_TYPE = "application/json";
    private final WebClient webClient;


    public ReportApi() {
        this.webClient = WebClient.builder()
            .baseUrl(GIBDD_API_BASE_URL)
            .build();
    }


    @Cacheable("vin")
    public String getGibddInfo(String number) {
        LOGGER.info("Receiving data from an external api:{}, vin:{} ",GIBDD_API_BASE_URL,number);
        return webClient.get()
            .uri("auto?types=history,dtp,restrict,diagnosticCard&vin=" + number + "&key=" + GIBDD_TOKEN)
            .retrieve()
            .bodyToMono(String.class).block();
    }




}
