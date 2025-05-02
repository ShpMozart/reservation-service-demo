package com.mozart.reserve.controller;

import com.mozart.reserve.dto.CheckReservationRequest;
import com.mozart.reserve.dto.ResponseWrapper;
import com.mozart.reserve.service.CheckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/check")
public class CheckController {
    private final CheckService checkService;

    public CheckController(CheckService checkService) {
        this.checkService = checkService;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> checkReservation(@RequestBody CheckReservationRequest request) {
        Map<String, Object> result = checkService.checkSeatReservation(request.getSeatNumber(), request.getUserId());

        int code = (int) result.getOrDefault("code", -99);
        String message = (String) result.getOrDefault("message", "Unknown result");

        ResponseWrapper response = new ResponseWrapper(code, message);
        return ResponseEntity.ok(response);
    }
}
