package csd230.lab1.controllers;

import csd230.lab1.entities.MagazineEntity;
import csd230.lab1.repositories.MagazineRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Magazines", description = "REST API for Magazines")
@RestController
@RequestMapping("/api/rest/magazines")
public class MagazineRestController {

    private final MagazineRepository repository;

    public MagazineRestController(MagazineRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "List all magazines")
    @GetMapping
    public List<MagazineEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Get magazine by ID")
    @GetMapping("/{id}")
    public MagazineEntity one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MagazineNotFoundException(id));
    }

    @Operation(summary = "Create magazine")
    @PostMapping
    public MagazineEntity create(@RequestBody MagazineEntity magazine) {
        return repository.save(magazine);
    }

    @Operation(summary = "Update magazine")
    @PutMapping("/{id}")
    public MagazineEntity update(@RequestBody MagazineEntity newMagazine, @PathVariable Long id) {
        return repository.findById(id)
                .map(mag -> {
                    mag.setTitle(newMagazine.getTitle());
                    mag.setPrice(newMagazine.getPrice());
                    mag.setCopies(newMagazine.getCopies());
                    return repository.save(mag);
                })
                .orElseGet(() -> {
                    newMagazine.setId(id);
                    return repository.save(newMagazine);
                });
    }

    @Operation(summary = "Delete magazine")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}