package dev.victormoraes.adapters.out.persistence.repositories;

import dev.victormoraes.adapters.out.persistence.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    Optional<VehicleEntity> findByPlate(String plate);
}