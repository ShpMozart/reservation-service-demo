package com.mozart.reserve.kafka.producer;

import com.mozart.reserve.dto.ConfirmRequestDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConfirmProducer {
    private final KafkaTemplate<String, ConfirmRequestDTO> kafkaTemplate;
    ConfirmProducer(KafkaTemplate<String, ConfirmRequestDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    private static final String TOPIC = "seat-reservation-confirm";

    public void sendConfirmRequest(ConfirmRequestDTO request) {
        kafkaTemplate.send(TOPIC, request);
    }
}
