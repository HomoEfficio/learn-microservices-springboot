package io.homo_efficio.lmsb.gamification.controller;

import io.homo_efficio.lmsb.gamification.domain.LeaderBoardRow;
import io.homo_efficio.lmsb.gamification.service.LeaderBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
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
@Slf4j
public class LeaderBoardController {

    private final LeaderBoardService leaderBoardService;
    private final Environment env;

    @GetMapping
    public ResponseEntity<List<LeaderBoardRow>> getLeaders() {
        log.info("Method {} is invoked at {}",
                Thread.currentThread().getStackTrace()[1], env.getProperty("server.port"));
        return ResponseEntity.ok(leaderBoardService.getCurrentLeaderBoard());
    }
}
