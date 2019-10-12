package io.homo_efficio.lmsb.gamification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.homo_efficio.lmsb.gamification.domain.LeaderBoardRow;
import io.homo_efficio.lmsb.gamification.service.LeaderBoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-12
 */
@WebMvcTest(LeaderBoardController.class)
public class LeaderBoardControllerTest {

    @MockBean
    private LeaderBoardService leaderBoardService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<List<LeaderBoardRow>> jsonLeaderBoardRows;


    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getLeaders() throws Exception {
        // given
        List<LeaderBoardRow> leaderBoardRows = new ArrayList<>();
        Random randomUserId = new Random();
        Random randomTotalScore = new Random();
        for (int i = 0 ; i < 10 ; i++) {
            long userId = randomUserId.nextInt(50) + 1;
            long totalScore = (randomTotalScore.nextInt(7000) + 1L) / 10L;
            LeaderBoardRow leaderBoardRow = new LeaderBoardRow(userId, totalScore);
            leaderBoardRows.add(leaderBoardRow);
        }
        given(leaderBoardService.getCurrentLeaderBoard())
                .willReturn(leaderBoardRows);

        // when
        MockHttpServletResponse response = mvc.perform(get("/leaders").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonLeaderBoardRows.write(leaderBoardRows).getJson());

    }
}
