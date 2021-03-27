package com.automobil.backend.exeption;

public class CLientException extends Exception {
    public CLientException() {
        super("This name already exists");
    }
}
