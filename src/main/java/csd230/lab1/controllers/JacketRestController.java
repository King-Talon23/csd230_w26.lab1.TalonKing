package csd230.lab1.controllers;

import csd230.lab1.entities.JacketEntity;
import csd230.lab1.repositories.JacketRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Jacket REST API", description = "JSON API for managing jackets")
@RestController
@RequestMapping("/api/rest/jackets")
@CrossOrigin(origins = "*")
public class JacketRestController {

    private final JacketRepository repository;

    public JacketRestController(JacketRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get all jackets")
    @GetMapping
    public List<JacketEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new jacket")
    @PostMapping
    public JacketEntity newJacket(@RequestBody JacketEntity jacket) {
        return repository.save(jacket);
    }

    @Operation(summary = "Get jacket by ID")
    @GetMapping("/{id}")
    public JacketEntity one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Jacket not found with ID: " + id));
    }

    @Operation(summary = "Update or replace jacket")
    @PutMapping("/{id}")
    public JacketEntity replaceJacket(@RequestBody JacketEntity newJacket, @PathVariable Long id) {
        return repository.findById(id)
                .map(j -> {
                    j.setPrice(newJacket.getPrice());
                    j.setInsulated(newJacket.isInsulated());
                    j.setSize(newJacket.getSize());
                    j.setCopies(newJacket.getCopies());
                    return repository.save(j);
                })
                .orElseGet(() -> {
                    newJacket.setId(id);
                    return repository.save(newJacket);
                });
    }

    @Operation(summary = "Delete jacket by ID")
    @DeleteMapping("/{id}")
    public void deleteJacket(@PathVariable Long id) {
        repository.deleteById(id);
    }
}