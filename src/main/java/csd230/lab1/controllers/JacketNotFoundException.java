package csd230.lab1.controllers;

public class JacketNotFoundException extends RuntimeException {
    public JacketNotFoundException(Long id) {
        super("Could not find jacket with ID: " + id);
    }
}