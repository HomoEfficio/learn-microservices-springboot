package io.homo_efficio.lmsb.gamification.service;

import io.homo_efficio.lmsb.gamification.domain.LeaderBoardRow;
import io.homo_efficio.lmsb.gamification.repository.BadgeCardRepository;
import io.homo_efficio.lmsb.gamification.repository.ScoreCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-12
 */
@Service
@Transactional
@RequiredArgsConstructor
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private final ScoreCardRepository scoreCardRepository;


    @Override
    @Transactional(readOnly = true)
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        return scoreCardRepository.findFirst10();
    }
}
