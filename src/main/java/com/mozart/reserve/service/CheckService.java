package com.mozart.reserve.service;

import com.mozart.reserve.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CheckService {

    private final RedisService redisService;
    private final SeatRepository seatRepository;

    public CheckService(RedisService redisService, SeatRepository seatRepository) {
        this.redisService = redisService;
        this.seatRepository = seatRepository;
    }

    public Map<String, Object> checkSeatReservation(String seatNumber, Long userId) {
        Map<String, Object> result = new HashMap<>();
        String reservedUserId = redisService.getReservedUserId(seatNumber);

        if (reservedUserId != null) {
            if (reservedUserId.equals(userId.toString())) {
                result.put("code", 1);
                result.put("message", "Seat is reserved for this user in Redis");
            } else {
                result.put("code", -1);
                result.put("message", "Seat is reserved for a different user in Redis");
            }
            return result;
        }

        boolean isSeatBooked = seatRepository.existsBySeatNumberAndBookedAndBookedByUserId(seatNumber, true, userId);

        if (isSeatBooked) {
            result.put("code", 1);
            result.put("message", "Seat is reserved for this user in the database");
        } else {
            result.put("code", -1);
            result.put("message", "Seat is not reserved for this user");
        }

        return result;
    }
}
