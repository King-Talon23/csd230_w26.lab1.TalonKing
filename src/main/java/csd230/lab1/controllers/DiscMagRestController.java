package csd230.lab1.controllers;

import csd230.lab1.entities.DiscMagEntity;
import csd230.lab1.repositories.DiscMagRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "DiscMags", description = "REST API for DiscMag")
@RestController
@RequestMapping("/api/rest/discmags")
public class DiscMagRestController {

    private final DiscMagRepository repository;

    public DiscMagRestController(DiscMagRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "List all disc mags")
    @GetMapping
    public List<DiscMagEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Get disc mag by ID")
    @GetMapping("/{id}")
    public DiscMagEntity one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DiscMagNotFoundException(id));
    }

    @Operation(summary = "Create disc mag")
    @PostMapping
    public DiscMagEntity create(@RequestBody DiscMagEntity entity) {
        return repository.save(entity);
    }

    @Operation(summary = "Update disc mag")
    @PutMapping("/{id}")
    public DiscMagEntity update(@RequestBody DiscMagEntity newEntity, @PathVariable Long id) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setTitle(newEntity.getTitle());
                    entity.setPrice(newEntity.getPrice());
                    entity.setCopies(newEntity.getCopies());
                    return repository.save(entity);
                })
                .orElseGet(() -> {
                    newEntity.setId(id);
                    return repository.save(newEntity);
                });
    }

    @Operation(summary = "Delete disc mag")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}