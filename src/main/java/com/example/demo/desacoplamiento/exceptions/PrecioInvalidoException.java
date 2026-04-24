package com.example.demo.desacoplamiento.exceptions;

public class PrecioInvalidoException extends RuntimeException {
    public PrecioInvalidoException(String message) {
        super(message);
    }
}
