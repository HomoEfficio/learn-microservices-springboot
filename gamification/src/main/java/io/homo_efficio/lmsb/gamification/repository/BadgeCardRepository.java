package io.homo_efficio.lmsb.gamification.repository;

import io.homo_efficio.lmsb.gamification.domain.BadgeCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-12
 */
public interface BadgeCardRepository extends JpaRepository<BadgeCard, Long> {

    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(Long userId);
}
