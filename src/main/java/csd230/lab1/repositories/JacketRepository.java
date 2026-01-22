package csd230.lab1.repositories;

import csd230.lab1.entities.JacketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JacketRepository extends JpaRepository<JacketEntity, Long> {

    List<JacketEntity> findByInsulated(boolean insulated);

    List<JacketEntity> findBySize(String size);

    List<JacketEntity> findByPriceLessThan(double price);
}
