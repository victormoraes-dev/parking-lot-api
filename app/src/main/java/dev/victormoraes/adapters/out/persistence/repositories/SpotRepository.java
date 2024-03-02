package dev.victormoraes.adapters.out.persistence.repositories;

import dev.victormoraes.adapters.out.persistence.entities.SpotEntity;
import dev.victormoraes.domain.vehicle.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpotRepository extends JpaRepository<SpotEntity, Long> {
    @Query("SELECT DISTINCT s FROM SpotEntity s WHERE s.vehicleType = :vehicleType AND s.isFree = true")
    List<SpotEntity> findByAllowedVehicleTypeAndIsFree(@Param("vehicleType") VehicleType vehicleType);

}