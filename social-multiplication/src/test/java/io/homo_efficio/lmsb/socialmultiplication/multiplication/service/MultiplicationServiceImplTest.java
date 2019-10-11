package io.homo_efficio.lmsb.socialmultiplication.multiplication.service;

import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.Multiplication;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.MultiplicationAttempt;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.User;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.event.EventDispatcher;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.event.MultiplicationSolvedEvent;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.repository.MultiplicationAttemptRepository;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
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

    @Mock
    private EventDispatcher eventDispatcher;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        multiplicationService = new MultiplicationServiceImpl(
                randomGeneratorService, attemptRepository, userRepository, eventDispatcher);
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
        MultiplicationSolvedEvent event = new MultiplicationSolvedEvent(
                verifiedAttempt.getId(), verifiedAttempt.getUser().getId(), verifiedAttempt.isCorrect()
        );

        // when
        boolean result = multiplicationService.checkAttempt(multiplicationAttempt);

        // then
        assertThat(result).isTrue();
        verify(attemptRepository).save(verifiedAttempt);
        verify(eventDispatcher).send(event);
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
        MultiplicationSolvedEvent event = new MultiplicationSolvedEvent(
                multiplicationAttempt.getId(), multiplicationAttempt.getUser().getId(), multiplicationAttempt.isCorrect()
        );

        // when
        boolean result = multiplicationService.checkAttempt(multiplicationAttempt);

        // then
        assertThat(result).isFalse();
        verify(attemptRepository).save(multiplicationAttempt);
        verify(eventDispatcher).send(event);
    }

    // EventDispatcher가 Mock이라 그런지 몰라도
    // checkAttempt()에 @Transactional이 붙어 있고
    // save() 전에 send() 호출 코드가 있고,
    // save() 시 예외 발생하더라도 verify()에서는 send()가 호출되는 것으로 나옴
    @Disabled
    @Test
    public void event_will_not_be_sent_when_exception_occurs() {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("homo efficio");
        MultiplicationAttempt multiplicationAttempt =
                new MultiplicationAttempt(user, multiplication, 3000, false);
        MultiplicationAttempt verifiedAttempt =
                new MultiplicationAttempt(user, multiplication, 3000, true);
        given(userRepository.findByAlias("homo efficio"))
                .willReturn(Optional.empty());
        MultiplicationSolvedEvent event = new MultiplicationSolvedEvent(
                verifiedAttempt.getId(), verifiedAttempt.getUser().getId(), verifiedAttempt.isCorrect()
        );

        given(attemptRepository.save(verifiedAttempt))
                .willThrow(new RuntimeException("Error while saving MultiplicationAttempt: " + verifiedAttempt));

        // when
        Throwable throwable = catchThrowable(() -> multiplicationService.checkAttempt(multiplicationAttempt));

        // then
        verify(attemptRepository).save(verifiedAttempt);
        verify(eventDispatcher).send(event);
        assertThat(throwable).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("MultiplicationAttempt");
    }

    @Test
    public void retrieveStatsTest() {
        // given
        Multiplication multiplication = new Multiplication(30, 80);
        User user = new User("Homo Efficio");
        given(userRepository.findByAlias("Homo Efficio"))
                .willReturn(Optional.empty());
        MultiplicationAttempt attempt1 = new MultiplicationAttempt(user, multiplication, 3010, false);
        MultiplicationAttempt attempt2 = new MultiplicationAttempt(user, multiplication, 3080, false);
        MultiplicationAttempt attempt3 = new MultiplicationAttempt(user, multiplication, 2400, false);
        ArrayList<MultiplicationAttempt> requestAttempts = Lists.newArrayList(attempt1, attempt2, attempt3);
        given(attemptRepository.findTop5ByUser_AliasOrderByIdDesc("Homo Efficio"))
                .willReturn(requestAttempts);
        multiplicationService.checkAttempt(attempt1);
        multiplicationService.checkAttempt(attempt2);
        multiplicationService.checkAttempt(attempt3);

        // when
        List<MultiplicationAttempt> stats = multiplicationService.getStatsForUser("Homo Efficio");

        // then
        assertThat(stats).isEqualTo(requestAttempts);
    }
}
