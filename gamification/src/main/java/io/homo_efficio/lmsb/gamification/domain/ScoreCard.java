package io.homo_efficio.lmsb.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-11
 */
@Entity
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class ScoreCard {

    public static final int DEFAULT_SCORE = 10;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCORE_CARD_ID")
    private Long id;

    private final Long userId;
    private final Long attemptId;
    @Column(name = "SCORE_TS")
    private final long scoreTimestamp;
    private final int score;

    // For JSON/JPA
    public ScoreCard() {
        this(null, null, 0, 0);
    }

    public ScoreCard(Long userId, Long attemptId) {
        this(userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);
    }
}
