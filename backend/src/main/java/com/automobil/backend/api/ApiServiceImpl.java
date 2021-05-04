package com.automobil.backend.api;

import com.automobil.backend.api.Pars.Parser;
import com.automobil.backend.dto.ReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ApiServiceImpl implements ApiService {
    private final ReportApi reportApi;
    private final Parser parser;
    private final CacheSupport cacheSupport;


    @Autowired
    public ApiServiceImpl(ReportApi reportApi, Parser parser, CacheSupport cacheSupport) {
        this.reportApi = reportApi;
        this.parser = parser;
        this.cacheSupport = cacheSupport;
    }

    @Override
    public ReportDto getGibddInfo(String vin) {
    String json = reportApi.getGibddInfo(vin);
//      cacheSupport.getCache();
    return parser.parseGibdd(json);
   //     return null;
    }

    @Override
    public ReportDto getTaxiGibddinfo(String vin, String number) {
        return null;
    }

    @Override
    public boolean workInTaxi(String number) {
        return false;
    }
}
