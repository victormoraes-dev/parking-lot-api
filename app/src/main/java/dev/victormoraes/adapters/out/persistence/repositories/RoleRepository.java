package dev.victormoraes.adapters.out.persistence.repositories;

import dev.victormoraes.adapters.out.persistence.entities.RoleEntity;
import dev.victormoraes.adapters.out.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String name);
}