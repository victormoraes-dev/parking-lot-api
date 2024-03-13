package dev.victormoraes.usecases.ports;

import dev.victormoraes.domain.Spot;

public interface CreateSpotPort {

    Spot createSpot(Spot spot);
}
