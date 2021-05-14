package com.automobil.backend.api;

import com.automobil.backend.api.Pars.Parser;
import com.automobil.backend.dto.ReportDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl implements ApiService {
    private static final Logger LOGGER= LogManager.getLogger(ApiServiceImpl.class.getName());
    private final ReportApi reportApi;
    private final Parser parser;



    @Autowired
    public ApiServiceImpl(ReportApi reportApi, Parser parser) {
        this.reportApi = reportApi;

        this.parser = parser;


    }

    @Override
    public ReportDto getGibddInfo(String vin){
        LOGGER.info("in getTaxiInfo vin{}",vin);
        String json = reportApi.getGibddInfo(vin);
        return parser.parseGibdd(json);
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
