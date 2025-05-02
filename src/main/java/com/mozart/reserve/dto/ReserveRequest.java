package com.mozart.reserve.dto;

import lombok.Getter;
import lombok.Setter;


public class ReserveRequest {
    private String seatNumber;
    private Long userId;

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
