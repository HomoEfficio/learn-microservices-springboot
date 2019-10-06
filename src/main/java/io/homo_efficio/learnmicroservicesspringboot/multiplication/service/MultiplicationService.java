package io.homo_efficio.learnmicroservicesspringboot.multiplication.service;

import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.Multiplication;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-06
 */
public interface MultiplicationService {

    /**
     * Creates a Multiplication object with 2 randomly generated factors between 11 and 99.
     *
     * @return a Multiplication object with random access
     */
    Multiplication createRandomMultiplication();
}
