package dev.victormoraes.adapters.in;

import dev.victormoraes.adapters.exceptions.NotFoundException;
import dev.victormoraes.adapters.in.dtos.ResponseWrapper;
import dev.victormoraes.adapters.in.dtos.ticket.TicketRequestDTO;
import dev.victormoraes.adapters.in.dtos.ticket.TicketResponseDTO;
import dev.victormoraes.adapters.mappers.TicketMapper;
import dev.victormoraes.domain.Ticket;
import dev.victormoraes.domain.result.Result;
import dev.victormoraes.domain.users.Customer;
import dev.victormoraes.domain.vehicle.Vehicle;
import dev.victormoraes.domain.vehicle.VehicleFactory;
import dev.victormoraes.usecases.CreatingTicketUseCase;
import dev.victormoraes.usecases.FetchingTicketUseCase;
import dev.victormoraes.usecases.TicketFinalizationUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final CreatingTicketUseCase useCase;

    private final FetchingTicketUseCase fetchingTicketUseCase;
    private final TicketFinalizationUseCase ticketFinalizationUseCase;

    private final HttpServletRequest request;

    public TicketController(CreatingTicketUseCase useCase, FetchingTicketUseCase fetchingTicketUseCase, TicketFinalizationUseCase ticketFinalizationUseCase, HttpServletRequest request) {
        this.useCase = useCase;
        this.fetchingTicketUseCase = fetchingTicketUseCase;
        this.ticketFinalizationUseCase = ticketFinalizationUseCase;
        this.request = request;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<TicketResponseDTO>> createTicket(@RequestBody @Valid TicketRequestDTO ticketRequestDTO)
            throws URISyntaxException {

        Vehicle vehicle = VehicleFactory.getVehicle(VehicleFactory.vehicleTypeFactory.get(ticketRequestDTO.vehicle().type()));
        vehicle.setColor(ticketRequestDTO.vehicle().color());
        vehicle.setPlate(ticketRequestDTO.vehicle().plate());
        vehicle.setModel(ticketRequestDTO.vehicle().model());

        var user = new Customer(ticketRequestDTO.username());
        Result<Ticket> ticketResult = useCase.enterParkingLot(user, vehicle, ticketRequestDTO.vehicle().type());

        if (!ticketResult.isSuccess()) {
            return ResponseEntity.unprocessableEntity().body(ResponseWrapper.error(ticketResult.getErrorMessage()));
        }

        TicketResponseDTO ticketResponseDTO = TicketMapper.toResponseDTO(ticketResult.getResult());

        String currentUrl = request.getRequestURL().toString();

        return ResponseEntity.created(new URI(currentUrl + "/" + ticketResponseDTO.ticketId()))
                .body(ResponseWrapper.success(ticketResponseDTO));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<ResponseWrapper<TicketResponseDTO>> getTicketById(@PathVariable Long ticketId) {
        Result<Ticket> ticketResult = fetchingTicketUseCase.fetchTicketById(ticketId);

        if (!ticketResult.isSuccess()) {

            if (ticketResult.getThrownException() instanceof NotFoundException) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.unprocessableEntity().body(ResponseWrapper.error(ticketResult.getErrorMessage()));
        }

        TicketResponseDTO ticketResponseDTO = TicketMapper.toResponseDTO(ticketResult.getResult());
        return ResponseEntity.ok(ResponseWrapper.success(ticketResponseDTO));
    }

    @PatchMapping("/{ticketId}/finalize")
    public ResponseEntity<ResponseWrapper<TicketResponseDTO>> finalizeTicket(@PathVariable Long ticketId) {

        Result<Ticket> ticketResult = ticketFinalizationUseCase.finalizeTicket(ticketId);

        if (!ticketResult.isSuccess()) {
            return ResponseEntity.badRequest().body(ResponseWrapper.error(ticketResult.getErrorMessage()));
        }

        TicketResponseDTO ticketResponseDTO = TicketMapper.toResponseDTO(ticketResult.getResult());
        return ResponseEntity.ok(ResponseWrapper.success(ticketResponseDTO));
    }
}
