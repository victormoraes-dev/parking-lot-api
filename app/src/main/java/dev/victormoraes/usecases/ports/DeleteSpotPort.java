package dev.victormoraes.usecases.ports;

import dev.victormoraes.domain.result.Result;

public interface DeleteSpotPort {
    Result<Void> deleteSpot(Long spotId);
}