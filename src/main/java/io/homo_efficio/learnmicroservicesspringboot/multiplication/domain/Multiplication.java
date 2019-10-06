package io.homo_efficio.learnmicroservicesspringboot.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-06
 */
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public final class Multiplication {

    private final int factorA;
    private final int factorB;

    public Multiplication() {
        this(0, 0);
    }
}
