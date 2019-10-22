package io.homo_efficio.lmsb.socialmultiplication.multiplication.controller;

import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.MultiplicationAttempt;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
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
@Slf4j
public class MultiplicationAttemptController {

    private final MultiplicationService multiplicationService;
    private final Environment env;

    @PostMapping
    public ResponseEntity<MultiplicationAttempt> postResult(@RequestBody MultiplicationAttempt attempt) {
        log.info("Method {} is invoked at {}",
                Thread.currentThread().getStackTrace()[1], env.getProperty("server.port"));
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

    @GetMapping("/{attemptId}")
    public ResponseEntity<MultiplicationAttempt> findMultiplicationAttempt(
            @PathVariable("attemptId") Long attemptId) {
        return ResponseEntity.ok(multiplicationService.getAttemptById(attemptId));
    }
}
