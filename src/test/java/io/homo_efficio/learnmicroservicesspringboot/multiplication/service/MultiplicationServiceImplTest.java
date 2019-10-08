package io.homo_efficio.learnmicroservicesspringboot.multiplication.service;

import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.Multiplication;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.MultiplicationAttempt;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.User;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.repository.MultiplicationAttemptRepository;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-06
 */
public class MultiplicationServiceImplTest {

    private MultiplicationService multiplicationService;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    private MultiplicationAttemptRepository attemptRepository;

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        multiplicationService = new MultiplicationServiceImpl(
                randomGeneratorService, attemptRepository, userRepository);
    }

    @Test
    public void createRandomMultiplicationTest() {
        // given
        given(randomGeneratorService.generateRandomFactor())
                .willReturn(50, 30);

        // when
        Multiplication multiplication = multiplicationService.createRandomMultiplication();

        // then
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        // FIXME Multiplication에 lombok 적용하면서 result 필드가 제거됨. 책 구성 오류인 듯
//        assertThat(multiplication.getResult()).isEqualTo(1500);
    }

    @Test
    public void checkCorrectAttemptTest() {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("homo efficio");
        MultiplicationAttempt multiplicationAttempt =
                new MultiplicationAttempt(user, multiplication, 3000, false);
        MultiplicationAttempt verifiedAttempt =
                new MultiplicationAttempt(user, multiplication, 3000, true);
        given(userRepository.findByAlias("homo efficio"))
                .willReturn(Optional.empty());

        // when
        boolean result = multiplicationService.checkAttempt(multiplicationAttempt);

        // then
        assertThat(result).isTrue();
        verify(attemptRepository).save(verifiedAttempt);
    }

    @Test
    public void checkWrongAttemptTest() {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("homo efficio");
        MultiplicationAttempt multiplicationAttempt =
                new MultiplicationAttempt(user, multiplication, 5000, false);
        given(userRepository.findByAlias("homo efficio"))
                .willReturn(Optional.empty());

        // when
        boolean result = multiplicationService.checkAttempt(multiplicationAttempt);

        // then
        assertThat(result).isFalse();
        verify(attemptRepository).save(multiplicationAttempt);
    }
}
