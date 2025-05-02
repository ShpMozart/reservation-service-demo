package com.mozart.reserve.service;

import com.mozart.reserve.model.Seat;
import com.mozart.reserve.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfirmService{

    private final SeatRepository seatRepository;
    ConfirmService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Transactional
    public void confirmSeat(String seatNumber, Long userId) {
        Seat seat = seatRepository.findBySeatNumber(seatNumber)
                .orElseThrow(() -> new RuntimeException("seatNumber not found : " + seatNumber));

        if (seat.getBooked()) {
            throw new RuntimeException("Seat already booked.");
        }

        seat.setBooked(true);
        seat.setBookedByUserId(userId);

        // چون optimistic locking داریم (فیلد version)، اگر در این لحظه کسی دیگه آپدیت کرده باشه، save خطا میده
        seatRepository.save(seat);
    }
}
