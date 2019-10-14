package io.homo_efficio.lmsb.socialmultiplication.multiplication.service;

import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.Multiplication;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.MultiplicationAttempt;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.User;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.event.EventDispatcher;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.event.MultiplicationSolvedEvent;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.repository.MultiplicationAttemptRepository;
import io.homo_efficio.lmsb.socialmultiplication.multiplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-06
 */
@Service
@RequiredArgsConstructor
public class MultiplicationServiceImpl implements MultiplicationService {

    private final RandomGeneratorService randomGeneratorService;
    private final MultiplicationAttemptRepository attemptRepository;
    private final UserRepository userRepository;
    private final EventDispatcher eventDispatcher;

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Transactional
    @Override
    public boolean checkAttempt(MultiplicationAttempt attempt) {
        Optional<User> optUser = userRepository.findByAlias(attempt.getUser().getAlias());

        Assert.isTrue(!attempt.isCorrect(), "You should not send an attempt marked as true!!");

        boolean correct = attempt.getResultAttempt() ==
                attempt.getMultiplication().getFactorA() *
                        attempt.getMultiplication().getFactorB();

        MultiplicationAttempt checkedAttempt = new MultiplicationAttempt(optUser.orElse(attempt.getUser()),
                attempt.getMultiplication(),
                attempt.getResultAttempt(),
                correct);
        attemptRepository.save(checkedAttempt);

        eventDispatcher.send(
                new MultiplicationSolvedEvent(
                    checkedAttempt.getId(), checkedAttempt.getUser().getId(), checkedAttempt.isCorrect()
                )
        );

        return correct;
    }

    @Override
    public List<MultiplicationAttempt> getStatsForUser(String userAlias) {
        return attemptRepository.findTop5ByUser_AliasOrderByIdDesc(userAlias);
    }

    @Override
    public MultiplicationAttempt getAttemptById(Long attemptId) {
        return attemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("MultiplicationAttempt [%d] is not found.", attemptId)));
    }
}
