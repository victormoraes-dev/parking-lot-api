package dev.victormoraes.usecases;

import dev.victormoraes.domain.Ticket;
import org.springframework.stereotype.Component;

@Component
public class ExitingParkingLotUseCase {

    public Ticket exitParkingLot(Ticket ticket) {

        // validate ticket

        //ticket.finalizeTicket();

        return ticket;
    }

}
