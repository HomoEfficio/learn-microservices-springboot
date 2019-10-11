package io.homo_efficio.lmsb.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-11
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class LeaderBoardRow {

    private final Long userId;
    private final Long totalScore;

    // For JSON/JPA

    public LeaderBoardRow() {
        this(0L, 0L);
    }
}
