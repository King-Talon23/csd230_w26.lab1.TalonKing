package csd230.lab1.controllers;

import csd230.lab1.entities.TicketEntity;
import csd230.lab1.repositories.TicketRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tickets", description = "REST API for Tickets")
@RestController
@RequestMapping("/api/rest/tickets")
public class TicketRestController {

    private final TicketRepository repository;

    public TicketRestController(TicketRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "List all tickets")
    @GetMapping
    public List<TicketEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Get ticket by ID")
    @GetMapping("/{id}")
    public TicketEntity one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Operation(summary = "Create ticket")
    @PostMapping
    public TicketEntity create(@RequestBody TicketEntity ticket) {
        return repository.save(ticket);
    }

    @Operation(summary = "Update ticket")
    @PutMapping("/{id}")
    public TicketEntity update(@RequestBody TicketEntity newTicket, @PathVariable Long id) {
        return repository.findById(id)
                .map(ticket -> {
                    ticket.setDescription(newTicket.getDescription());
                    ticket.setPrice(newTicket.getPrice());
                    return repository.save(ticket);
                })
                .orElseGet(() -> {
                    newTicket.setId(id);
                    return repository.save(newTicket);
                });
    }

    @Operation(summary = "Delete ticket")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}