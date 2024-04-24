package dev.victormoraes.usecases.ports;

import dev.victormoraes.domain.Spot;

public interface UpdateSpotPort {
    Spot updateSpot(Long spotId, Spot spot);
}
