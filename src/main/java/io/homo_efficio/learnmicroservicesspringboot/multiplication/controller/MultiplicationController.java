package io.homo_efficio.learnmicroservicesspringboot.multiplication.controller;

import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.Multiplication;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-07
 */
@RestController
@RequestMapping("/multiplications")
@RequiredArgsConstructor
public class MultiplicationController {

    private final MultiplicationService multiplicationService;

    @GetMapping("/random")
    public Multiplication getRandomMultiplication() {
        return multiplicationService.createRandomMultiplication();
    }
}
