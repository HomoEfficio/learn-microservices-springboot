package io.homo_efficio.learnmicroservicesspringboot.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Identifies the attempt from a {@link User} to solve a {@link Multiplication}.
 *
 * @author homo.efficio@gmail.com
 * created on 2019-10-07
 */
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public final class MultiplicationAttempt {

    private final User user;
    private final Multiplication multiplication;
    private final int resultAttempt;

    public MultiplicationAttempt() {
        this.user = null;
        this.multiplication = null;
        this.resultAttempt = -1;
    }
}
