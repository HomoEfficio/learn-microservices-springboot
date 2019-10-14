package io.homo_efficio.lmsb.socialmultiplication.multiplication.service;

import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.Multiplication;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.MultiplicationAttempt;

import java.util.List;

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
     * @return true if the {@link MultiplicationAttempt} matches the result, or false
     */
    boolean checkAttempt(final MultiplicationAttempt resultAttempt);

    /**
     *
     * @param userAlias
     * @return Last N MultiplicationAttempts that user attempted
     */
    List<MultiplicationAttempt> getStatsForUser(String userAlias);

    /**
     *
     * @param attemptId
     * @return MultiplicationAttempt that has attemptId
     */
    MultiplicationAttempt getAttemptById(Long attemptId);
}
