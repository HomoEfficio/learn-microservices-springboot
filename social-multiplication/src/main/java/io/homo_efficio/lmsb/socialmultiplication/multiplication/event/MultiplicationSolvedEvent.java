package io.homo_efficio.lmsb.socialmultiplication.multiplication.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-11
 */
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class MultiplicationSolvedEvent implements Serializable {

    private final Long multiplicationResultAttemptId;
    private final Long userId;
    private final boolean correct;
}
