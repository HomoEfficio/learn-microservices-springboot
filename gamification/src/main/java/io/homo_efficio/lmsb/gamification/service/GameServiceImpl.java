package io.homo_efficio.lmsb.gamification.service;

import io.homo_efficio.lmsb.gamification.client.MultiplicationAttemptClient;
import io.homo_efficio.lmsb.gamification.client.dto.MultiplicationAttempt;
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
    private final MultiplicationAttemptClient multiplicationAttemptClient;
    int LUCKY_NUMBER = 42;

    @Override
    public GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct) {
        List<ScoreCard> scoreCards = scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId);
        giveFirstAttemptBadge(userId, scoreCards);
        Integer totalScoreForUser = scoreCardRepository.getTotalScoreForUser(userId);
        int totalScore = totalScoreForUser != null ? totalScoreForUser : 0;
        if (correct) {
            ScoreCard scoreCard = new ScoreCard(userId, attemptId);
            ScoreCard dbScoreCard = scoreCardRepository.save(scoreCard);
            totalScore += ScoreCard.DEFAULT_SCORE;
            giveCorrectBadge(userId, totalScore);
        }
        List<Badge> badges = getBadges(userId);

        return new GameStats(userId, totalScore, badges);
    }

    private List<Badge> getBadges(Long userId) {
        List<BadgeCard> badgeCards = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);
        List<Badge> badges = new ArrayList<>();
        for (BadgeCard badgeCard: badgeCards) {
            badges.add(badgeCard.getBadge());
        }
        return badges;
    }

    private void giveCorrectBadge(Long userId, int totalScore) {
        switch (totalScore) {
            case 10:
                badgeCardRepository.save(new BadgeCard(userId, Badge.FIRST_WON));
                break;
            case 100:
                badgeCardRepository.save(new BadgeCard(userId, Badge.BRONZE_MULTIPLICATOR));
                break;
            case 500:
                badgeCardRepository.save(new BadgeCard(userId, Badge.SILVER_MULTIPLICATOR));
                break;
            case 1000:
                badgeCardRepository.save(new BadgeCard(userId, Badge.GOLD_MULTIPLICATOR));
                break;
        }
    }

    private void giveFirstAttemptBadge(Long userId, List<ScoreCard> scoreCards) {
        if (scoreCards.isEmpty()) {
            BadgeCard badgeCard = new BadgeCard(userId, Badge.FIRST_ATTEMPT);
            BadgeCard dbBadgeCard = badgeCardRepository.save(badgeCard);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public GameStats retrieveTotalStatsForUser(Long userId) {
        Integer totalScoreForUser = scoreCardRepository.getTotalScoreForUser(userId);
        int totalScore = totalScoreForUser != null ? totalScoreForUser : 0;
        List<Badge> badges = getBadges(userId);

        return new GameStats(userId, totalScore, badges);
    }
}
