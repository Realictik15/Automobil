package com.automobil.backend.rest;

import com.automobil.backend.api.ApiService;
import com.automobil.backend.api.ReportApi;
import com.automobil.backend.dto.AuthenticationRequestDto;
import com.automobil.backend.dto.ReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class Test {
    @Autowired
    private ApiService apiService;

    @GetMapping(value = "/{vin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportDto> getAllAdvert(@PathVariable("vin") String vin) throws IOException {
      if(vin.isEmpty()){
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

        return new ResponseEntity<>(apiService.getGibddInfo(vin), HttpStatus.OK);
    }
    @GetMapping(value = "/num/{vin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll(@PathVariable("vin") String vin) throws IOException {
        if(vin.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(apiService.workInTaxi(vin)){
            return new ResponseEntity<>("True", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("False", HttpStatus.OK);
        }
    }
//    @GetMapping(value = "/1", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<AuthenticationRequestDto>> getAllAvert() throws UnsupportedEncodingException {
//        List<AuthenticationRequestDto> a = new ArrayList<>();
//        a.add(taxiApi.getTestCache("a"));
//        return new ResponseEntity<>(a, HttpStatus.OK);
//    }

//    @GetMapping(value = "/t", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> get() throws UnsupportedEncodingException {
//        List<AuthenticationRequestDto> a = new ArrayList<>();
//        taxiApi.getCache();
//        return new ResponseEntity<>(a, HttpStatus.OK);
//    }
}
