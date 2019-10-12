package io.homo_efficio.lmsb.gamification.service;

import io.homo_efficio.lmsb.gamification.domain.GameStats;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-12
 */
public interface GameService {

    GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct);

    GameStats retrieveTotalStatsForUser(Long userId);
}
