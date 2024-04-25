package dev.victormoraes.usecases.ports;

import dev.victormoraes.domain.Spot;
import dev.victormoraes.domain.result.Result;

public interface GetSpotPort {
    Result<Spot> getSpot(Long spotId);
}