package com.automobil.backend.exeption;

import lombok.Data;

@Data
public class CLientException extends Exception {
    String problem;

    public CLientException(String problem) {
        super(String.format("This '%s' already exists", problem));
    }
}
