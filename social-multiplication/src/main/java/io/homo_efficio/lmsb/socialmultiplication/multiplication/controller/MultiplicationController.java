package io.homo_efficio.lmsb.socialmultiplication.multiplication.controller;

import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.Multiplication;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
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
@Slf4j
public class MultiplicationController {

    private final MultiplicationService multiplicationService;
    private final Environment env;

    @GetMapping("/random")
    public Multiplication getRandomMultiplication() {
        log.info("Method {} is invoked at {}",
                Thread.currentThread().getStackTrace()[1], env.getProperty("server.port"));
        return multiplicationService.createRandomMultiplication();
    }
}
