package dev.victormoraes.adapters.mappers;

import dev.victormoraes.adapters.in.dtos.spot.SpotRequestDTO;
import dev.victormoraes.adapters.in.dtos.spot.SpotResponseDTO;
import dev.victormoraes.adapters.out.persistence.entities.SpotEntity;
import dev.victormoraes.domain.Spot;

public class SpotMapper {

    public static Spot toDomain(SpotEntity spotEntity) {
        return new Spot(spotEntity.getSpotId(),
                spotEntity.getFloor(),
                spotEntity.getPosition(),
                spotEntity.isFree(),
                spotEntity.getVehicleType());
    }

    public static Spot toDomain(SpotRequestDTO spotRequestDTO) {
        return new Spot(spotRequestDTO.getFloor(),
                spotRequestDTO.getPosition(),
                spotRequestDTO.isFree(),
                spotRequestDTO.getVehicleType());
    }

    public static SpotEntity toEntity(Spot spot) {
        SpotEntity entity = new SpotEntity();
        entity.setFloor(spot.getFloor());
        entity.setPosition(spot.getPosition());
        entity.setFree(spot.isFree());
        entity.setVehicleType(spot.getVehicleType());
        return entity;
    }

    public static SpotResponseDTO toResponseDTO(Spot spot) {
        return new SpotResponseDTO(spot.getSpotId(),
                spot.getFloor(),
                spot.getPosition(),
                spot.isFree(),
                spot.getVehicleType());
    }
}
