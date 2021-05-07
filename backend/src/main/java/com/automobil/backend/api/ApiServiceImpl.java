package com.automobil.backend.api;

import com.automobil.backend.api.Pars.Parser;
import com.automobil.backend.dto.ReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApiServiceImpl implements ApiService {
    private final ReportApi reportApi;
    private final TaxiApi taxiApi;
    private final Parser parser;



    @Autowired
    public ApiServiceImpl(ReportApi reportApi, TaxiApi taxiApi, Parser parser) {
        this.reportApi = reportApi;
        this.taxiApi = taxiApi;
        this.parser = parser;


    }

    @Override
    public ReportDto getGibddInfo(String vin){
        String json = reportApi.getGibddInfo(vin);
        return parser.parseGibdd(json);
    }

    @Override
    public ReportDto getTaxiGibddinfo(String vin, String number) {
        return null;
    }

    @Override
    public boolean workInTaxi(String number) {
        return taxiApi.getGibddInfo(number);
    }
}
