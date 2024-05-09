package dev.victormoraes.domain;

import dev.victormoraes.domain.users.User;
import dev.victormoraes.domain.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.String.format;
import static java.util.Objects.isNull;

public class Ticket {

    private Long ticketId;
    private final Vehicle vehicle;
    private final User user;
    private final LocalDateTime startTime;
    private LocalDateTime endTime;

    public Ticket(Long ticketId, Vehicle vehicle, User user, LocalDateTime startTime, LocalDateTime endTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Ticket(Vehicle vehicle, User user, LocalDateTime startTime) {
        this.vehicle = vehicle;
        this.user = user;
        this.startTime = startTime;
    }

    public Long getTicketId() {
        return ticketId;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String printTicket() {

        return format("[Vehicle] code=%s | model=%s | color=%s [Ticket] startTime=%s | endTime=%s",
                vehicle.getPlate(), vehicle.getModel(), vehicle.getColor(), formatTicketDate(startTime), formatTicketDate(endTime));
    }

    public boolean isValidTicket() {

        return startTime.isBefore(LocalDateTime.now()) && isNull(endTime);
    }

    private String formatTicketDate(LocalDateTime date) {

        if (isNull(date)) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }
}
