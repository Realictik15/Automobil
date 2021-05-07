package com.automobil.backend.api;

import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TaxiApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportApi.class);

    private static final String TAXI_TOKEN = "fb417fd10e7195c1422cf30fa3ac0b61";
    private static final String GIBDD_API_BASE_URL = "https://apidata.mos.ru/v1/datasets/655/rows?$top=20&$skip=0&$filter=Cells/";
    private static final String OMDB_MIME_TYPE = "application/json";
    private final WebClient webClient;


    public TaxiApi() {
        this.webClient = WebClient.builder()
            .baseUrl(GIBDD_API_BASE_URL)
            .build();
    }


    @Cacheable("number")
    public boolean getGibddInfo(String number) {
        LOGGER.info("Receiving data from an external api:{}, number:{} ", GIBDD_API_BASE_URL, number);
        String taxi = webClient.get()
            .uri("VehicleNumber eq " + number + "&api_key=" + TAXI_TOKEN)
            .retrieve()
            .bodyToMono(String.class).block();
        if(taxi.isEmpty()){
            return false;
        }else {
            return true;
        }

    }


}
