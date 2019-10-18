package io.homo_efficio.lmsb.gamification.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-16
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@JsonDeserialize(using = MultiplicationAttemptDeserializer.class)
public class MultiplicationAttempt {

    private final String userAlias;
    private final int factorA;
    private final int factorB;
    private final int attempt;
    private final boolean correct;

    // For JSON/JPA

    protected MultiplicationAttempt() {
        this.userAlias = null;
        this.factorA = -1;
        this.factorB = -1;
        this.attempt = -1;
        this.correct = false;
    }

    public boolean isLucky(int luckyNumber) {
        return factorA == luckyNumber || factorB == luckyNumber;
    }
}
