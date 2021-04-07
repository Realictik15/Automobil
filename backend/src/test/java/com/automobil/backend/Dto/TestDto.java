package com.automobil.backend.Dto;

import com.automobil.backend.dto.CountriesDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestDto {
    private static final Logger logger = LoggerFactory.getLogger(TestDto.class);

    @Test
    void countryDto() throws ParseException {

        CountriesDto countriesDto = new CountriesDto();
        countriesDto.setInfo("some info");
        countriesDto.setTitle("Title");
        countriesDto.setId((long) 1);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CountriesDto>> violations = validator.validate(countriesDto);
        violations.forEach(x -> logger.error((x.getMessage())));


    }

    @Test
    void pars() {
        String hello = "C:\\Users\\Artem\\Desktop\\assets\\advettisment\\7\\hbjbccx,fsf,dfdf";
        int index1 = hello.lastIndexOf('\\');
        String s = hello.substring(index1 + 1);
        String[] a = s.split(",");
        System.out.println(Arrays.toString(a));
        assertEquals(a.length, 3);

    }

}
