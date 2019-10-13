package io.homo_efficio.lmsb.gamification.controller;

import io.homo_efficio.lmsb.gamification.domain.GameStats;
import io.homo_efficio.lmsb.gamification.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-12
 */
@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class UserStatsController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<GameStats> getStatsForUser(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(gameService.retrieveTotalStatsForUser(userId));
    }
}
