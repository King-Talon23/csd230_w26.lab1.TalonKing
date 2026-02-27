package csd230.lab1.controllers;

public class TShirtNotFoundException extends RuntimeException {
    public TShirtNotFoundException(Long id) {
        super("Could not find t-shirt with ID: " + id);
    }
}