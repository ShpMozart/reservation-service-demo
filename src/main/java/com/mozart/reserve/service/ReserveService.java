package com.mozart.reserve.service;

import com.mozart.reserve.dto.ReserveRequest;
import com.mozart.reserve.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReserveService {

    private final RedisService redisService;
    private final SeatRepository seatRepository;

    public ReserveService(RedisService redisService, SeatRepository seatRepository) {
        this.redisService = redisService;
        this.seatRepository = seatRepository;
    }

    public Map<String, Object> reserveSeat(ReserveRequest reserveRequest) {
        Map<String, Object> result = new HashMap<>();

        String currentReservation = redisService.getReservedUserId(reserveRequest.getSeatNumber());

        if (currentReservation != null) {
            result.put("code", -1);
            result.put("message", "Seat is already reserved in Redis");
            return result;
        }

        boolean isSeatBooked = seatRepository.existsBySeatNumberAndBooked(reserveRequest.getSeatNumber(), true);
        if (isSeatBooked) {
            result.put("code", -1);
            result.put("message", "Seat is already booked in the database");
            return result;
        }

        redisService.reserveSeat(reserveRequest.getSeatNumber(), reserveRequest.getUserId());

        result.put("code", 1);
        result.put("message", "Seat reserved successfully");
        return result;
    }
}
