package io.homo_efficio.lmsb.gamification.service;

import io.homo_efficio.lmsb.gamification.domain.Badge;
import io.homo_efficio.lmsb.gamification.domain.BadgeCard;
import io.homo_efficio.lmsb.gamification.domain.GameStats;
import io.homo_efficio.lmsb.gamification.domain.ScoreCard;
import io.homo_efficio.lmsb.gamification.repository.BadgeCardRepository;
import io.homo_efficio.lmsb.gamification.repository.ScoreCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-12
 */
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final BadgeCardRepository badgeCardRepository;
    private final ScoreCardRepository scoreCardRepository;

    @Override
    public GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct) {
        List<ScoreCard> scoreCards = scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId);
        int score = 0;
        if (correct) {

        } else {
            if (scoreCards.isEmpty()) {
                BadgeCard badgeCard = new BadgeCard(userId, Badge.FIRST_ATTEMPT);
                BadgeCard dbBadgeCard = badgeCardRepository.save(badgeCard);
            }
        }
        List<BadgeCard> badgeCards = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);
        List<Badge> badges = new ArrayList<>();
        for (BadgeCard badgeCard: badgeCards) {
            badges.add(badgeCard.getBadge());
        }
        return new GameStats(userId, score, badges);
    }

    @Override
    public GameStats retrieveStatsForUser(Long userId) {
        return null;
    }
}
