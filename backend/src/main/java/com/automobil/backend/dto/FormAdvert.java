package com.automobil.backend.dto;

import com.automobil.backend.transfer.*;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

@Data
public class FormAdvert {
    private String carbodyTitle;
    private Long clientsId;
    private String marksTitle;
    private String modelTitle;
    private Long generationsId;
    private Long modificationsId;
    private String available;
    private Double price;
    private String colour;
    private Double mileage;
    private Integer broken;
    private String city;
    private String place;
    private Long phone;
    private Integer swap;
    private Integer pts;
    private Integer owners;
    private String gosnumber;
    private String buydays;
    private String dayofwarrantys;
    private String vin;
    private Long sts;
    private String commentns;
    private List<MultipartFile> files;
}
