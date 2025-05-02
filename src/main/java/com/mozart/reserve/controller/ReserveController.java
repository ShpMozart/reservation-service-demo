package com.mozart.reserve.controller;

import com.mozart.reserve.dto.ReserveRequest;
import com.mozart.reserve.dto.ResponseWrapper;
import com.mozart.reserve.service.ReserveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    private final ReserveService reserveService;

    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> reserveSeat(@RequestBody ReserveRequest request) {
        Map<String, Object> result = reserveService.reserveSeat(request);

        int code = (int) result.getOrDefault("code", -99);
        String message = (String) result.getOrDefault("message", "Unknown error");

        return ResponseEntity.ok(new ResponseWrapper(code, message));
    }
}
