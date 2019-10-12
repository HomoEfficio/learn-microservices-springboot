package io.homo_efficio.lmsb.gamification.service;

import io.homo_efficio.lmsb.gamification.domain.Badge;
import io.homo_efficio.lmsb.gamification.domain.BadgeCard;
import io.homo_efficio.lmsb.gamification.domain.GameStats;
import io.homo_efficio.lmsb.gamification.domain.ScoreCard;
import io.homo_efficio.lmsb.gamification.repository.BadgeCardRepository;
import io.homo_efficio.lmsb.gamification.repository.ScoreCardRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-12
 */
public class GameServiceImplTest {

    private GameService gameService;

    @Mock
    private BadgeCardRepository badgeCardRepository;

    @Mock
    private ScoreCardRepository scoreCardRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        gameService = new GameServiceImpl(
                badgeCardRepository, scoreCardRepository
        );
    }

    @Test
    public void firstAttemptBadgeTest() {
        // given
        Long userId = 3L;
        Long attemptId = 31L;
        given(scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId))
                .willReturn(Collections.emptyList());
        BadgeCard badgeCard = new BadgeCard(userId, Badge.FIRST_ATTEMPT);
        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId))
                .willReturn(Collections.singletonList(badgeCard));
        given(scoreCardRepository.getTotalScoreForUser(userId))
                .willReturn(0);

        // when
        GameStats gameStats = gameService.newAttemptForUser(userId, attemptId, false);

        // then
        assertThat(gameStats.getBadges()).containsOnly(Badge.FIRST_ATTEMPT);
        assertThat(gameStats.getScore()).isEqualTo(0);
    }

    @Test
    public void firstWonBadgeTest() {
        // given
        Long userId = 3L;
        Long attemptId = 31L;
        given(scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId))
                .willReturn(Collections.emptyList());
        BadgeCard firstAttemptBadgeCard = new BadgeCard(userId, Badge.FIRST_ATTEMPT);
        BadgeCard firstWonBadgeCard = new BadgeCard(userId, Badge.FIRST_WON);

        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId))
                .willReturn(Lists.newArrayList(firstWonBadgeCard, firstAttemptBadgeCard));

        given(scoreCardRepository.getTotalScoreForUser(userId))
                .willReturn(0);

        // when
        GameStats gameStats = gameService.newAttemptForUser(userId, attemptId, true);

        // then
        assertThat(gameStats.getScore()).isEqualTo(10);
        assertThat(gameStats.getBadges()).contains(Badge.FIRST_ATTEMPT, Badge.FIRST_WON);
    }

    @Test
    public void bronzeMedalTest() {
        // given
        Long userId = 5L;
        Long attemptId = 51L;
        List<ScoreCard> scoreCards = new ArrayList<>();
        for (int i = 0 ; i < 9 ; i++) {
            scoreCards.add(new ScoreCard(userId, attemptId + i));
        }
        given(scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId))
                .willReturn(scoreCards);

        BadgeCard firstAttemptBadgeCard = new BadgeCard(userId, Badge.FIRST_ATTEMPT);
        BadgeCard firstWonBadgeCard = new BadgeCard(userId, Badge.FIRST_WON);
        BadgeCard bronzeBadgeCard = new BadgeCard(userId, Badge.BRONZE_MULTIPLICATOR);

        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId))
                .willReturn(Lists.newArrayList(bronzeBadgeCard, firstWonBadgeCard, firstAttemptBadgeCard));

        given(scoreCardRepository.getTotalScoreForUser(userId))
                .willReturn(90);

        // when
        GameStats gameStats = gameService.newAttemptForUser(userId, attemptId, true);

        // then
        assertThat(gameStats.getScore()).isEqualTo(100);
        assertThat(gameStats.getBadges()).contains(Badge.BRONZE_MULTIPLICATOR, Badge.FIRST_ATTEMPT, Badge.BRONZE_MULTIPLICATOR);
    }

    @Test
    public void silverMedalTest() {

    }

    @Test
    public void goldMedalTest() {

    }

    @Test
    public void rightAttemptTest() {

    }

    @Test
    public void wrongAttemptTest() {

    }
}
