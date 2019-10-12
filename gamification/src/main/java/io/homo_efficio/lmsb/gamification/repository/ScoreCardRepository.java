package io.homo_efficio.lmsb.gamification.repository;

import io.homo_efficio.lmsb.gamification.domain.LeaderBoardRow;
import io.homo_efficio.lmsb.gamification.domain.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-12
 */
public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long> {

    @Query("select sum(s.score) from ScoreCard s where s.userId = :userId group by s.userId")
    Integer getTotalScoreForUser(@Param("userId") Long userId);

    @Query("" +
            "select " +
            "new io.homo_efficio.lmsb.gamification.domain.LeaderBoardRow(s.userId, sum(s.score)) " +
            "from ScoreCard s " +
            "group by s.userId " +
            "order by sum(s.score) " +
            "desc ")
    List<LeaderBoardRow> findFirst10();

    List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(Long userId);
}
