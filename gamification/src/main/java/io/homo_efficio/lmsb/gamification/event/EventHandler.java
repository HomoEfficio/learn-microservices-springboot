package io.homo_efficio.lmsb.gamification.event;

import io.homo_efficio.lmsb.gamification.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-14
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class EventHandler {

    private final GameService gameService;

    @RabbitListener(queues = "${multiplication.queue}")
    public void handleMultiplicationSolved(MultiplicationSolvedEvent event) {
        try {
            gameService.newAttemptForUser(
                    event.getUserId(),
                    event.getMultiplicationResultAttemptId(),
                    event.isCorrect());
        } catch (Exception e) {
            log.error("Error when trying to process MultiplicationSolvedEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
