package io.homo_efficio.learnmicroservicesspringboot.multiplication.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Event Dispatcher(== Event Publisher)
 *
 * @author homo.efficio@gmail.com
 * created on 2019-10-11
 */
@Component
public class EventDispatcher {

    private final RabbitTemplate rabbitTemplate;
    private final String multiplicationExchange;
    private final String multiplicationSolvedRoutingKey;

    public EventDispatcher(final RabbitTemplate rabbitTemplate,
                           @Value("${multiplication.exchange}") final String multiplicationExchange,
                           @Value("${multiplication.solved.key}") final String multiplicationSolvedRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.multiplicationExchange = multiplicationExchange;
        this.multiplicationSolvedRoutingKey = multiplicationSolvedRoutingKey;
    }

    public void send(final MultiplicationSolvedEvent multiplicationSolvedEvent) {
        rabbitTemplate.convertAndSend(
                multiplicationExchange,
                multiplicationSolvedRoutingKey,
                multiplicationSolvedEvent
        );
    }
}
