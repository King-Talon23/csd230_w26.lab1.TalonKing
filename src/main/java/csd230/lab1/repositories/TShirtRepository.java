package csd230.lab1.repositories;

import csd230.lab1.entities.TShirtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TShirtRepository extends JpaRepository<TShirtEntity, Long> {

    List<TShirtEntity> findBySleeveLength(String sleeveLength);

    List<TShirtEntity> findBySize(String size);

    List<TShirtEntity> findByPriceBetween(double min, double max);
}
