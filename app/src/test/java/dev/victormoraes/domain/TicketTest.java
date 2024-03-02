package dev.victormoraes.domain;

import dev.victormoraes.domain.vehicle.Car;
import dev.victormoraes.domain.vehicle.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TicketTest {

    private static Vehicle vehicle;

    @BeforeAll
    public static void init() {
        vehicle = new Car();
        vehicle.setCode("GIF8KNB");
        vehicle.setModel("HB20");
        vehicle.setColor("SAND");
    }

    @Test
    public void shouldPrintTicketDetails() {
        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        assertNotNull(ticket.printTicket());
    }

    @Test
    public void givenTicketIsOpenedShouldReturnAnEmptyStringForEndTime() {

        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        Assertions.assertTrue(ticket.printTicket().contains("endTime="));
    }

    @Test
    public void whenATicketIsFinalizedShouldHaveNotNullEndTime() {
        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.finalizeTicket();
        assertNotNull(ticket.getEndTime());
    }

    @Test
    public void whenATicketIsNotFinalizedShouldHaveANullEndTime() {
        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        assertNull(ticket.getEndTime());
    }
}