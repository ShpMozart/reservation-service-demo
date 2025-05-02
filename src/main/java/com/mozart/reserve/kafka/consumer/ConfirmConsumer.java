package com.mozart.reserve.kafka.consumer;

import com.mozart.reserve.dto.ConfirmRequestDTO;
import com.mozart.reserve.service.ConfirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service

public class ConfirmConsumer {
    private final ConfirmService confirmService;
    ConfirmConsumer(ConfirmService confirmService) {
        this.confirmService = confirmService;
    }
    @KafkaListener(topics = "seat-reservation-confirm", groupId = "seat-reservation-group", containerFactory = "confirmRequestKafkaListenerContainerFactory")
    public void consumeConfirmRequest(ConfirmRequestDTO requestDTO) {
        System.out.println("Received ConfirmRequestDTO: " + requestDTO.toString());
        confirmService.confirmSeat(requestDTO.getSeatNumber(), requestDTO.getUserId());
    }
}
