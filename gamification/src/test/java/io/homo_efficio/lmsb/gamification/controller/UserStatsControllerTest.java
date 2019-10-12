package io.homo_efficio.lmsb.gamification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.homo_efficio.lmsb.gamification.domain.Badge;
import io.homo_efficio.lmsb.gamification.domain.GameStats;
import io.homo_efficio.lmsb.gamification.service.GameService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-12
 */
@WebMvcTest(UserStatsController.class)
public class UserStatsControllerTest {

    @MockBean
    private GameService gameService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<GameStats> jsonGameStats;


    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getUserStats() throws Exception {
        // given
        Long userId = 2L;
        List<Badge> badges = Lists.newArrayList(Badge.BRONZE_MULTIPLICATOR, Badge.FIRST_WON, Badge.FIRST_ATTEMPT);
        GameStats gameStats = new GameStats(userId, 380, badges);
        given(gameService.retrieveTotalStatsForUser(userId))
                .willReturn(gameStats);

        // when
        MockHttpServletResponse response = mvc.perform(get("/stats").param("userId", String.valueOf(userId)))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonGameStats.write(gameStats).getJson());
    }
}
