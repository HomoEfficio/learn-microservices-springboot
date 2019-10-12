package io.homo_efficio.lmsb.gamification.controller;

import io.homo_efficio.lmsb.gamification.domain.LeaderBoardRow;
import io.homo_efficio.lmsb.gamification.service.LeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-12
 */
@RestController
@RequestMapping("/leaders")
@RequiredArgsConstructor
public class LeaderBoardController {

    private final LeaderBoardService leaderBoardService;

    @GetMapping
    public ResponseEntity<List<LeaderBoardRow>> getLeaders() {
        return ResponseEntity.ok(leaderBoardService.getCurrentLeaderBoard());
    }
}
