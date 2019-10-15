package io.homo_efficio.lmsb.gamification.client;

import io.homo_efficio.lmsb.gamification.client.dto.MultiplicationAttempt;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-16
 */
public interface MultiplicationAttemptClient {
    MultiplicationAttempt retrieveMultiplicationAttemptById(Long attemptId);
}
