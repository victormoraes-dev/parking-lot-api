package dev.victormoraes.domain;

import dev.victormoraes.domain.users.Anonymous;
import dev.victormoraes.domain.vehicle.Car;
import dev.victormoraes.domain.vehicle.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TicketTest {

    private static Vehicle vehicle;

    @BeforeAll
    public static void init() {
        vehicle = new Car();
        vehicle.setPlate("GIF8KNB");
        vehicle.setModel("HB20");
        vehicle.setColor("SAND");
    }

    @Test
    public void shouldPrintTicketDetails() {
        Ticket ticket = new Ticket(vehicle, new Anonymous(), LocalDateTime.now());
        assertNotNull(ticket.printTicket());
    }

    @Test
    public void givenTicketIsOpenedShouldReturnAnEmptyStringForEndTime() {

        Ticket ticket = new Ticket(vehicle, new Anonymous(), LocalDateTime.now());
        Assertions.assertTrue(ticket.printTicket().contains("endTime="));
    }

    @Test
    public void whenATicketIsFinalizedShouldHaveNotNullEndTime() {
        Ticket ticket = new Ticket(vehicle, new Anonymous(), LocalDateTime.now());
        ticket.finalizeTicket();
        assertNotNull(ticket.getEndTime());
    }

    @Test
    public void whenATicketIsNotFinalizedShouldHaveANullEndTime() {
        Ticket ticket = new Ticket(vehicle, new Anonymous(), LocalDateTime.now());
        assertNull(ticket.getEndTime());
    }
}