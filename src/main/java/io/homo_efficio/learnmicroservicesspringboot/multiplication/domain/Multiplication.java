package io.homo_efficio.learnmicroservicesspringboot.multiplication.domain;

import lombok.Getter;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-06
 */
@Getter
public class Multiplication {

    private int factorA;
    private int factorB;

    private int result;

    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
        this.result = factorA * factorB;
    }

    @Override
    public String toString() {
        return "Multiplication{" +
                "factorA=" + factorA +
                ", factorB=" + factorB +
                ", result=" + result +
                '}';
    }
}
