package com.mozart.reserve.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "seats", uniqueConstraints = {
    @UniqueConstraint(columnNames = "seat_number")
})

public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "booked", nullable = false)
    private boolean booked = false;

    @Column(name = "booked_by_user_id")
    private Long bookedByUserId;

    @Version
    private Long version;

    public boolean getBooked() {
        return booked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public Long getBookedByUserId() {
        return bookedByUserId;
    }

    public void setBookedByUserId(Long bookedByUserId) {
        this.bookedByUserId = bookedByUserId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
