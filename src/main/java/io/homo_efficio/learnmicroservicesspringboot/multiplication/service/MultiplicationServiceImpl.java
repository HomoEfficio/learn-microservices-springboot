package io.homo_efficio.learnmicroservicesspringboot.multiplication.service;

import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.Multiplication;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.MultiplicationAttempt;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.repository.MultiplicationAttemptRepository;
import io.homo_efficio.learnmicroservicesspringboot.multiplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Override
    public boolean checkAttempt(MultiplicationAttempt attempt) {
        Assert.isTrue(!attempt.isCorrect(), "You should not send an attempt marked as true!!");
        boolean correct = attempt.getResultAttempt() ==
                attempt.getMultiplication().getFactorA() *
                        attempt.getMultiplication().getFactorB();
        return correct;
    }
}
