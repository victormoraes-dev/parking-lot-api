package dev.victormoraes.usecases;

import dev.victormoraes.domain.Ticket;
import dev.victormoraes.domain.result.Result;
import dev.victormoraes.domain.users.Anonymous;
import dev.victormoraes.domain.users.User;
import dev.victormoraes.domain.vehicle.Car;
import dev.victormoraes.domain.vehicle.Vehicle;
import dev.victormoraes.domain.vehicle.VehicleType;
import dev.victormoraes.usecases.ports.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatingATicketUseCaseTest {

    @Mock
    private SpotAvailabilityPort spotAvailabilityPort;
    @Mock
    private CreateTicketPort createTicketPort;
    @Mock
    private GetVehiclePort getVehiclePort;
    @Mock
    private CreateVehiclePort createVehiclePort;
    @Mock
    private CreateUserPort createUserPort;
    @Mock
    private GetUserPort getUserPort;

    @InjectMocks
    private CreatingATicketUseCase useCase;

    @Test
    public void whenANewVehicleIsEnteringTheParkingLotShouldCreateANewTicket() {

        Vehicle vehicle = new Car();
        User user = new Anonymous();
        Result<Ticket> ticketResult = useCase.enterParkingLot(user, vehicle, VehicleType.CAR);
        assertNotNull(ticketResult);
    }

    @Test
    public void whenANewVehicleIsEnteringTheParkingLotShouldCreateANewTicketWithFulfilledStartTime() {

        Vehicle vehicle = new Car();
        User user = new Anonymous();
        when(spotAvailabilityPort.checkSpotAvailability(VehicleType.CAR)).thenReturn(true);

        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setStartTime(LocalDateTime.now());
        when(createTicketPort.createTicket(any(), Mockito.eq(VehicleType.CAR))).thenReturn(ticket);

        Result<Ticket> ticketResult = useCase.enterParkingLot(user, vehicle, VehicleType.CAR);
        assertNotNull(ticketResult.getResult().getStartTime());
    }


}