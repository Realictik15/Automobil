package com.automobil.backend.Dto;

import com.automobil.backend.api.Pars.Parser;
import com.automobil.backend.dto.CountriesDto;
import com.automobil.backend.dto.ReportDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestDto {
    private static final Logger logger = LoggerFactory.getLogger(TestDto.class);
    private final JsonParser parser = new JsonParser();

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

    @Test
    void testParsingGibdd() throws IOException {
        Parser parser = new Parser();
        String text = new String(Files.readAllBytes(Paths.get("C:\\Users\\Artem\\Desktop\\testing.txt")), StandardCharsets.UTF_8);
        ReportDto reportDto = parser.parseGibdd(text);
        System.out.println(reportDto);

    }

    @Test
    void read() throws IOException {
        String text = new String(Files.readAllBytes(Paths.get("C:\\Users\\Artem\\Desktop\\cache.txt")), StandardCharsets.UTF_8);
        JsonElement jsonTree = parser.parse(text);
        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            System.out.println(jsonObject.get("XW8ZZZ7PZDG005302").toString());
        }
    }
}
