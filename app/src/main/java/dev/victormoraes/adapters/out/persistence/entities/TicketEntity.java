package dev.victormoraes.adapters.out.persistence.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @Column(name = "ticket_id")
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
    private VehicleEntity vehicle;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    public TicketEntity() {
    }

    public TicketEntity(VehicleEntity vehicle) {
        this.ticketId = Math.abs(new Random().nextLong());
        this.vehicle = vehicle;
        this.startTime = LocalDateTime.now();
    }

    public void finalizeTicket() {
        endTime = LocalDateTime.now();
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
