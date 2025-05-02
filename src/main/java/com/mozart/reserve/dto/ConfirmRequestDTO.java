package com.mozart.reserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ConfirmRequestDTO {
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

    @Override
    public String toString() {
        return "ConfirmRequestDTO{" +
                "seatNumber=" + seatNumber +
                ", userId=" + userId +
                '}';
    }

}
