package dev.victormoraes.usecases;

import dev.victormoraes.domain.Spot;
import dev.victormoraes.domain.result.Result;
import dev.victormoraes.domain.vehicle.VehicleType;
import dev.victormoraes.usecases.ports.CreateSpotPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatingSpotUseCaseTest {

    @Mock
    private CreateSpotPort createSpotPort;

    @InjectMocks
    private CreatingSpotUseCase creatingSpotUseCase;

    @Test
    void createSpot_ShouldReturnSuccessResult_WhenSpotIsCreated() {
        // Arrange
        Spot inputSpot = new Spot(1, "A10", true, VehicleType.CAR);
        Spot createdSpot = new Spot(1L, 1, "A10", true, VehicleType.CAR);
        when(createSpotPort.createSpot(inputSpot)).thenReturn(createdSpot);

        // Act
        Result<Spot> result = creatingSpotUseCase.createSpot(inputSpot);

        // Assert
        assertTrue(result.isSuccess());
        verify(createSpotPort, times(1)).createSpot(inputSpot);
    }
}