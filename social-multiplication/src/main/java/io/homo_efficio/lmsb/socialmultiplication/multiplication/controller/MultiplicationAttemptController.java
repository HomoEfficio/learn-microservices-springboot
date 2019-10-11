package io.homo_efficio.lmsb.socialmultiplication.multiplication.controller;

import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.MultiplicationAttempt;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<MultiplicationAttempt> postResult(@RequestBody MultiplicationAttempt attempt) {
        return ResponseEntity.ok(
                new MultiplicationAttempt(
                        attempt.getUser(),
                        attempt.getMultiplication(),
                        attempt.getResultAttempt(),
                        multiplicationService.checkAttempt(attempt)
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<MultiplicationAttempt>> getStats(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(multiplicationService.getStatsForUser(alias));
    }
}
