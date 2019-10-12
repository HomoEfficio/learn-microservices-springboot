package io.homo_efficio.lmsb.gamification.service;

import io.homo_efficio.lmsb.gamification.domain.Badge;
import io.homo_efficio.lmsb.gamification.domain.BadgeCard;
import io.homo_efficio.lmsb.gamification.domain.GameStats;
import io.homo_efficio.lmsb.gamification.domain.ScoreCard;
import io.homo_efficio.lmsb.gamification.repository.BadgeCardRepository;
import io.homo_efficio.lmsb.gamification.repository.ScoreCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

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

        // when
        GameStats gameStats = gameService.newAttemptForUser(userId, attemptId, false);

        // then
        assertThat(gameStats.getBadges()).containsOnly(Badge.FIRST_ATTEMPT);
        assertThat(gameStats.getScore()).isEqualTo(0);
    }

    @Test
    public void firstWonBadgeTest() {

    }

    @Test
    public void bronzeMedalTest() {

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
