package io.homo_efficio.lmsb.gamification.service;

import io.homo_efficio.lmsb.gamification.repository.BadgeCardRepository;
import io.homo_efficio.lmsb.gamification.repository.ScoreCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        gameService = new GameServiceImpl();
    }

    @Test
    public void firstAttemptBadgeTest() {

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
