package io.homo_efficio.lmsb.gamification.service;

import io.homo_efficio.lmsb.gamification.domain.LeaderBoardRow;

import java.util.List;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-12
 */
public interface LeaderBoardService {

    List<LeaderBoardRow> getCurrentLeaderBoard();
}
