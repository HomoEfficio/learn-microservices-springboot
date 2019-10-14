package io.homo_efficio.lmsb.socialmultiplication.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.Multiplication;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.MultiplicationAttempt;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.User;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.service.MultiplicationService;
import org.assertj.core.util.Lists;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(MultiplicationAttemptController.class)
public class MultiplicationAttemptControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<MultiplicationAttempt> jsonAttempt;
    private JacksonTester<MultiplicationAttempt> jsonResponse;
    private JacksonTester<List<MultiplicationAttempt>> jsonAttemptList;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void postResultReturnCorrect() throws Exception {
        genericParameterizedTest(true);
    }

    @Test
    public void postResultReturnNotCorrect() throws Exception {
        genericParameterizedTest(false);
    }

    private void genericParameterizedTest(final boolean correct) throws Exception {
        // given
        given(multiplicationService.checkAttempt(
                any(MultiplicationAttempt.class)
        )).willReturn(correct);
        User user = new User("Homo Efficio");
        Multiplication multiplication = new Multiplication(20, 30);
        MultiplicationAttempt attempt = new MultiplicationAttempt(user, multiplication, 600, false);

        // when
        MockHttpServletResponse response = mvc.perform(
                post("/results")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonAttempt.write(attempt).getJson())
        ).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonResponse.write(
                        new MultiplicationAttempt(
                                attempt.getUser(),
                                attempt.getMultiplication(),
                                attempt.getResultAttempt(),
                                correct)).getJson());
    }

    @Test
    public void getUserStats() throws Exception {
        // given
        User user = new User("Homo Efficio");
        Multiplication multiplication = new Multiplication(30, 80);
        MultiplicationAttempt attempt = new MultiplicationAttempt(user, multiplication, 2400, true);
        List<MultiplicationAttempt> recentAttempts = Lists.newArrayList(attempt, attempt);
        given(multiplicationService.getStatsForUser("Homo Efficio"))
                .willReturn(recentAttempts);

        // when
        MockHttpServletResponse response = mvc.perform(get("/results").param("alias", "Homo Efficio"))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonAttemptList.write(recentAttempts).getJson()
        );
    }

    @Test
    public void findResultAttempt() throws Exception {
        // given
        User user = new User("Homo Efficio");
        Multiplication multiplication = new Multiplication(10, 14);
        // TODO ID가 있는 Attempt를 어떻게 만들어 반환할 것인가
        Long attemptId = 33L;
        MultiplicationAttempt multiplicationAttempt = new MultiplicationAttempt(user, multiplication, 140, false);
        given(multiplicationService.getAttemptById(attemptId))
                .willReturn(multiplicationAttempt);

        // when
        MockHttpServletResponse response = mvc.perform(get("/results/" + attemptId).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonAttempt.write(multiplicationAttempt).getJson());
    }
}
