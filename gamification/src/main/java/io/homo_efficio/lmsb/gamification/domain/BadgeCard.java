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
public class BadgeCard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BADGE_CARD_ID")
    private Long id;

    private final Long userId;
    private final long badgeTimestamp;
    @Enumerated(EnumType.STRING)
    private final Badge badge;

    // Constructor for JSON/JPA
    public BadgeCard() {
        this(null, 0, null);
    }

    public BadgeCard(Long userId, Badge badge) {
        this(userId, System.currentTimeMillis(), badge);
    }
}
