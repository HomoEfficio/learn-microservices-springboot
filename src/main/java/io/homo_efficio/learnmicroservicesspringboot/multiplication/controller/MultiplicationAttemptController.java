package io.homo_efficio.learnmicroservicesspringboot.multiplication.controller;

import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.MultiplicationAttempt;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.service.MultiplicationService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-07
 */
@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class MultiplicationAttemptController {

    private final MultiplicationService multiplicationService;

    @PostMapping
    public ResponseEntity<ResultResponse> postResult(@RequestBody MultiplicationAttempt attempt) {
        return ResponseEntity.ok(
                new ResultResponse(multiplicationService.checkAttempt(attempt))
        );
    }

    @Getter
    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    public static final class ResultResponse {
        private final boolean correct;
    }
}
