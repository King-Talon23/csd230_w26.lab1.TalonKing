package csd230.lab1.controllers;

public class DiscMagNotFoundException extends RuntimeException {
    public DiscMagNotFoundException(Long id) {
        super("Could not find disc mag with ID: " + id);
    }
}