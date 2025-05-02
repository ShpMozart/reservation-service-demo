package com.mozart.reserve.repository;

import com.mozart.reserve.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findBySeatNumber(String seatNumber);
    boolean existsBySeatNumberAndBooked(String seatNumber, boolean booked);
    boolean existsBySeatNumberAndBookedAndBookedByUserId(String seatNumber, boolean booked, Long userId);

}
