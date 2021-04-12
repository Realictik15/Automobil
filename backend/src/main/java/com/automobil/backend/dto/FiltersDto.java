package com.automobil.backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FiltersDto {
   private String mark;
   private String model;
   private String carBody;
   private String dateStart;
   private String dateEnd;
   private String gearBox;
   private Integer mileage;
   private Long priceStart;
   private Long priceEnd;

}
