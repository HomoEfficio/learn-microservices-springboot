package io.homo_efficio.lmsb.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-11
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class GameStats {

    private final Long userId;
    private final int score;
    private final List<Badge> badges;

    // For JSON/JPA
    public GameStats() {
        this.userId = 0L;
        this.score = 0;
        this.badges = new ArrayList<>();
    }

    public static GameStats emptyStats(Long userId) {
        return new GameStats(userId, 0, Collections.emptyList());
    }

    public List<Badge> getBadges() {
        return Collections.unmodifiableList(badges);
    }
}
