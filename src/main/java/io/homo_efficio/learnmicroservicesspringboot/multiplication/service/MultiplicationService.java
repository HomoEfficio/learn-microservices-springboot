package io.homo_efficio.learnmicroservicesspringboot.multiplication.service;

import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.Multiplication;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.MultiplicationAttempt;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-06
 */
public interface MultiplicationService {

    /**
     * Creates a {@link Multiplication} object with 2 randomly generated factors between 11 and 99.
     *
     * @return a Multiplication object with random access
     */
    Multiplication createRandomMultiplication();

    /**
     * @return true if the {@link io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.MultiplicationAttempt} matches the result, or false
     */
    boolean checkAttempt(final MultiplicationAttempt resultAttempt);
}
