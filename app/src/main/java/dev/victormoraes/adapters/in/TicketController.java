package dev.victormoraes.adapters.in;

import dev.victormoraes.adapters.in.dtos.ResponseWrapper;
import dev.victormoraes.adapters.in.dtos.TicketRequestDTO;
import dev.victormoraes.adapters.in.dtos.TicketResponseDTO;
import dev.victormoraes.adapters.mappers.VehicleMapper;
import dev.victormoraes.domain.Ticket;
import dev.victormoraes.adapters.mappers.TicketMapper;
import dev.victormoraes.domain.result.Result;
import dev.victormoraes.domain.users.Customer;
import dev.victormoraes.domain.users.User;
import dev.victormoraes.domain.vehicle.Vehicle;
import dev.victormoraes.domain.vehicle.VehicleFactory;
import dev.victormoraes.domain.vehicle.VehicleType;
import dev.victormoraes.usecases.CreatingATicketUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final CreatingATicketUseCase useCase;
    private final TicketMapper ticketMapper;

    private final HttpServletRequest request;

    public TicketController(CreatingATicketUseCase useCase, TicketMapper ticketMapper, HttpServletRequest request) {
        this.useCase = useCase;
        this.ticketMapper = ticketMapper;
        this.request = request;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<TicketResponseDTO>> createTicket(@RequestBody TicketRequestDTO ticketRequestDTO)
            throws URISyntaxException {

        Vehicle vehicle = VehicleFactory.getVehicle(VehicleFactory.vehicleTypeFactory.get(ticketRequestDTO.vehicle().getType()));
        vehicle.setColor(ticketRequestDTO.vehicle().getColor());
        vehicle.setCode(ticketRequestDTO.vehicle().getCode());
        vehicle.setModel(ticketRequestDTO.vehicle().getModel());

        var user = new Customer(ticketRequestDTO.username());
        Result<Ticket> ticketResult = useCase.enterParkingLot(user, vehicle, ticketRequestDTO.vehicle().getType());

        if (!ticketResult.isSuccess()) {
            return ResponseEntity.unprocessableEntity().body(ResponseWrapper.error(ticketResult.getErrorMessage()));
        }

        TicketResponseDTO ticketResponseDTO = ticketMapper.toResponseDTO(ticketResult.getResult(),
                ticketRequestDTO.vehicle().getType());

        String currentUrl = request.getRequestURL().toString();

        return ResponseEntity.created(new URI(currentUrl + "/" + ticketResponseDTO.getTicketId()))
                .body(ResponseWrapper.success(ticketResponseDTO));
    }
}
