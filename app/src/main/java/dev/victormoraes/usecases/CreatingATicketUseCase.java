package dev.victormoraes.usecases;

import dev.victormoraes.domain.Ticket;
import dev.victormoraes.domain.result.Result;
import dev.victormoraes.domain.users.Anonymous;
import dev.victormoraes.domain.users.User;
import dev.victormoraes.domain.vehicle.Vehicle;
import dev.victormoraes.domain.vehicle.VehicleType;
import dev.victormoraes.usecases.ports.*;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class CreatingATicketUseCase {

    private final SpotAvailabilityPort spotAvailabilityPort;
    private final CreateTicketPort createTicketPort;
    private final GetVehiclePort getVehiclePort;
    private final CreateVehiclePort createVehiclePort;
    private final CreateUserPort createUserPort;
    private final GetUserPort getUserPort;

    public CreatingATicketUseCase(SpotAvailabilityPort spotAvailabilityPort, CreateTicketPort createTicketPort, GetVehiclePort getVehiclePort, CreateVehiclePort createVehiclePort, CreateUserPort createUserPort, GetUserPort getUserPort) {
        this.spotAvailabilityPort = spotAvailabilityPort;
        this.createTicketPort = createTicketPort;
        this.getVehiclePort = getVehiclePort;
        this.createVehiclePort = createVehiclePort;
        this.createUserPort = createUserPort;
        this.getUserPort = getUserPort;
    }

    public Result<Ticket> enterParkingLot(User user, Vehicle vehicle, VehicleType vehicleType) {

        if (!spotAvailabilityPort.checkSpotAvailability(vehicleType)) {
            Result<Ticket> result = new Result<>(false);
            result.setErrorMessage(String.format("There is no spots available for the car type: %s", vehicleType));
            return result;
        }

        var newTicket = new Ticket();
        var returnedVehicle = getVehicle(vehicle, vehicleType);
        newTicket.setVehicle(returnedVehicle);

        var returnedUser = getUser(user);
        newTicket.setUser(returnedUser);

        var createdTicket = createTicketPort.createTicket(newTicket, vehicleType);
        Result<Ticket> result = new Result<>(true);
        result.setResult(createdTicket);
        return result;
    }

    private Vehicle getVehicle(Vehicle vehicle, VehicleType vehicleType) {

        var returnedVehicle = getVehiclePort.getVehicle(vehicle.getCode(), vehicleType);
        return returnedVehicle.orElseGet(() -> createVehiclePort.createVehicle(vehicle));
    }

    private User getUser(User user) {

        if (isNull(user.getUsername())) {
            return createUserPort.createUser(new Anonymous());
        }

        var returnedUser = getUserPort.findUserByUsername(user.getUsername());
        return returnedUser.orElseGet(() -> createUserPort.createUser(new Anonymous()));
    }

}
