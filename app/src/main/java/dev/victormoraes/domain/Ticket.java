package dev.victormoraes.domain;

import dev.victormoraes.domain.users.User;
import dev.victormoraes.domain.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.String.format;
import static java.util.Objects.isNull;

public class Ticket {

    private Long ticketId;
    private Vehicle vehicle;
    private User user;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String printTicket() {

        return format("[Vehicle] code=%s | model=%s | color=%s [Ticket] startTime=%s | endTime=%s",
                vehicle.getPlate(), vehicle.getModel(), vehicle.getColor(), formatTicketDate(startTime), formatTicketDate(endTime));
    }

    public void finalizeTicket() {
        endTime = LocalDateTime.now();
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
