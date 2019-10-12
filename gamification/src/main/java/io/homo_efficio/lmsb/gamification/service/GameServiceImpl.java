package io.homo_efficio.lmsb.gamification.service;

import io.homo_efficio.lmsb.gamification.domain.Badge;
import io.homo_efficio.lmsb.gamification.domain.BadgeCard;
import io.homo_efficio.lmsb.gamification.domain.GameStats;
import io.homo_efficio.lmsb.gamification.domain.ScoreCard;
import io.homo_efficio.lmsb.gamification.repository.BadgeCardRepository;
import io.homo_efficio.lmsb.gamification.repository.ScoreCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-12
 */
@Service
@Transactional
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final BadgeCardRepository badgeCardRepository;
    private final ScoreCardRepository scoreCardRepository;

    @Override
    public GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct) {
        List<ScoreCard> scoreCards = scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId);
        giveFirstAttemptBadge(userId, scoreCards);
        int score = 0;
        if (correct) {
            giveFirstWonBadge(userId, scoreCards);
            score = ScoreCard.DEFAULT_SCORE;
            ScoreCard scoreCard = new ScoreCard(userId, attemptId);
            ScoreCard dbScoreCard = scoreCardRepository.save(scoreCard);
        } else {

        }
        List<BadgeCard> badgeCards = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);
        List<Badge> badges = new ArrayList<>();
        for (BadgeCard badgeCard: badgeCards) {
            badges.add(badgeCard.getBadge());
        }

        return new GameStats(userId, score, badges);
    }

    private void giveFirstWonBadge(Long userId, List<ScoreCard> scoreCards) {
        if (scoreCards.isEmpty()) {
            BadgeCard badgeCard = new BadgeCard(userId, Badge.FIRST_WON);
            BadgeCard dbBadgeCard = badgeCardRepository.save(badgeCard);
        }
    }

    private void giveFirstAttemptBadge(Long userId, List<ScoreCard> scoreCards) {
        if (scoreCards.isEmpty()) {
            BadgeCard badgeCard = new BadgeCard(userId, Badge.FIRST_ATTEMPT);
            BadgeCard dbBadgeCard = badgeCardRepository.save(badgeCard);
        }
    }

    @Override
    public GameStats retrieveStatsForUser(Long userId) {
        return null;
    }
}
