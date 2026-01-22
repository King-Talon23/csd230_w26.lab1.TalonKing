package csd230.lab1.repositories;

import csd230.lab1.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    List<TicketEntity> findByDescription(String description);

    List<TicketEntity> findByPriceLessThan(double price);

    List<TicketEntity> findByPriceBetween(double min, double max);
}
