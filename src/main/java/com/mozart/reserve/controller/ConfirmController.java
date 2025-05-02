package com.mozart.reserve.controller;

import com.mozart.reserve.dto.ConfirmRequestDTO;
import com.mozart.reserve.dto.ResponseWrapper;
import com.mozart.reserve.kafka.producer.ConfirmProducer;
import com.mozart.reserve.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/confirm")
public class ConfirmController {
    private final ConfirmProducer confirmProducer;
    private final SeatRepository seatRepository;
    ConfirmController(ConfirmProducer confirmProducer, SeatRepository seatRepository){
        this.confirmProducer = confirmProducer;
        this.seatRepository = seatRepository;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> confirmReservation(@RequestBody ConfirmRequestDTO request) {
        boolean isSeatBooked = seatRepository.existsBySeatNumberAndBooked(request.getSeatNumber(), true);
        if (isSeatBooked) {
            return ResponseEntity.ok(new ResponseWrapper(-1, "Seat is already booked in the database"));
        }
        confirmProducer.sendConfirmRequest(request);
        return ResponseEntity.ok(new ResponseWrapper(1, "Reservation request sent to Kafka for confirmation."));
    }
}
