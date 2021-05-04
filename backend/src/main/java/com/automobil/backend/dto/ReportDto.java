package com.automobil.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReportDto {
    private String engineNumber;
    private Integer buyYear;
    private Integer countOwners;
    private String lastOwnersDate;
    private Integer countAccidents;
    private List<AccidentsApi> accidents;
    private Integer mileage;
    private String lastDiagnostic;
    private List<RestrictoinApi> restrictoins;
    private boolean taxi;

}
