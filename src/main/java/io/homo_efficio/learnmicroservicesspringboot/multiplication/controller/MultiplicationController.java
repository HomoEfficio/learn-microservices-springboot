package io.homo_efficio.learnmicroservicesspringboot.multiplication.controller;

import io.homo_efficio.learnmicroservicesspringboot.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-07
 */
@RestController
@RequiredArgsConstructor
public class MultiplicationController {

    private final MultiplicationService multiplicationService;
}
