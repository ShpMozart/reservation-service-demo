package com.mozart.reserve.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final StringRedisTemplate redisTemplate;
    RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    private static final long RESERVATION_TTL_MINUTES = 5;

    public void reserveSeat(String seatNumber, Long userId) {
        String key = generateKey(seatNumber);
        redisTemplate.opsForValue().set(key, userId.toString(), RESERVATION_TTL_MINUTES, TimeUnit.MINUTES);
    }

    public String getReservedUserId(String seatNumber) {
        String key = generateKey(seatNumber);
        return redisTemplate.opsForValue().get(key);
    }

    public void releaseSeat(String seatNumber) {
        String key = generateKey(seatNumber);
        redisTemplate.delete(key);
    }

    private String generateKey(String seatNumber) {
        return "seat:reservation:" + seatNumber;
    }
}
