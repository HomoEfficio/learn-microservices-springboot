package io.homo_efficio.learnmicroservicesspringboot.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.Multiplication;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.MultiplicationAttempt;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.User;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.service.MultiplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(MultiplicationAttemptController.class)
public class MultipicationAttemptControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<MultiplicationAttempt> jsonAttempt;
    private JacksonTester<MultiplicationAttempt> jsonResponse;

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
        MultiplicationAttempt multiplicationAttempt = new MultiplicationAttempt(user, multiplication, 600, false);

        // when
        MockHttpServletResponse response = mvc.perform(
                post("/results")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonAttempt.write(multiplicationAttempt).getJson())
        ).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonResponse.write(new MultiplicationAttempt(user, multiplication, 600, correct)).getJson());
    }

}
