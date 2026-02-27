package csd230.lab1.controllers;

import csd230.lab1.entities.TShirtEntity;
import csd230.lab1.repositories.TShirtRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TShirt REST API", description = "JSON API for managing t-shirts")
@RestController
@RequestMapping("/api/rest/tshirts")
@CrossOrigin(origins = "*")
public class TShirtRestController {

    private final TShirtRepository repository;

    public TShirtRestController(TShirtRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get all t-shirts")
    @GetMapping
    public List<TShirtEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new t-shirt")
    @PostMapping
    public TShirtEntity newTShirt(@RequestBody TShirtEntity tShirt) {
        return repository.save(tShirt);
    }

    @Operation(summary = "Get t-shirt by ID")
    @GetMapping("/{id}")
    public TShirtEntity one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("TShirt not found with ID: " + id));
    }

    @Operation(summary = "Update or replace t-shirt")
    @PutMapping("/{id}")
    public TShirtEntity replaceTShirt(@RequestBody TShirtEntity newTShirt, @PathVariable Long id) {
        return repository.findById(id)
                .map(t -> {
                    t.setPrice(newTShirt.getPrice());
                    t.setSleeveLength(newTShirt.getSleeveLength());
                    t.setSize(newTShirt.getSize());
                    t.setCopies(newTShirt.getCopies());
                    return repository.save(t);
                })
                .orElseGet(() -> {
                    newTShirt.setId(id);
                    return repository.save(newTShirt);
                });
    }

    @Operation(summary = "Delete t-shirt by ID")
    @DeleteMapping("/{id}")
    public void deleteTShirt(@PathVariable Long id) {
        repository.deleteById(id);
    }
}