package com.automobil.backend.exeption;

import lombok.Data;

@Data
public class AdvertExeption extends Exception {
    String problem;

    public AdvertExeption(String problem) {
        super(String.format("There was a problem: '%s'", problem));
    }
}
