package dev.victormoraes.domain;

import dev.victormoraes.adapters.out.persistence.entities.TicketEntity;
import dev.victormoraes.adapters.out.persistence.entities.VehicleEntity;
import dev.victormoraes.domain.users.Anonymous;
import dev.victormoraes.domain.vehicle.Car;
import dev.victormoraes.domain.vehicle.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TicketEntityTest {

    private static VehicleEntity vehicle;

    @BeforeAll
    public static void init() {
        vehicle = new VehicleEntity.CarEntity();
        vehicle.setPlate("GIF8KNB");
        vehicle.setModel("HB20");
        vehicle.setColor("SAND");
    }

    @Test
    public void whenATicketIsFinalizedShouldHaveNotNullEndTime() {
        TicketEntity ticket = new TicketEntity(vehicle);
        ticket.finalizeTicket();
        assertNotNull(ticket.getEndTime());
    }

    @Test
    public void whenATicketIsNotFinalizedShouldHaveANullEndTime() {
        TicketEntity ticket = new TicketEntity(vehicle);
        assertNull(ticket.getEndTime());
    }
}