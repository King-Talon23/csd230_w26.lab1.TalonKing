package csd230.lab1.repositories;

import csd230.lab1.entities.ClothingItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothingItemRepository extends JpaRepository<ClothingItemEntity, Long> {
}
