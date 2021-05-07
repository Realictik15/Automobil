package com.automobil.backend.api;

import com.automobil.backend.dto.ReportDto;

import java.io.IOException;

public interface ApiService {
    ReportDto getGibddInfo(String vin);

    ReportDto getTaxiGibddinfo(String vin, String number);

    boolean workInTaxi(String number);

}
