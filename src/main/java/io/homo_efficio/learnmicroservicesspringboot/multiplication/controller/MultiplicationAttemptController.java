package io.homo_efficio.learnmicroservicesspringboot.multiplication.controller;

import io.homo_efficio.learnmicroservicesspringboot.multiplication.service.MultiplicationService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    @Getter
    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    private static final class ResultResponse {
        private final boolean correct;
    }
}
