package com.bnpp.kata.berlinclock.controller;

import com.bnpp.kata.berlinclock.exception.TimeFormatException;
import com.bnpp.kata.berlinclock.model.BerlinClockRequest;
import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.service.BerlinClockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;
import static com.bnpp.kata.berlinclock.constants.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${berlinclock.controller.path}")
public class BerlinClockController {

    private final BerlinClockService berlinClockService;

    @PostMapping("${berlinclock.endpoint.calculateBerlinClockTime}")
    public ResponseEntity<BerlinClockResponse> calculateBerlinClockTime(@RequestBody BerlinClockRequest request) {

        if (Objects.nonNull(request.getTime())) {
            return new ResponseEntity<>(berlinClockService.convertToBerlinTime(request.getTime()), HttpStatus.OK);
        }
        
        throw new TimeFormatException(INVALID_REQUEST);
    }
}
