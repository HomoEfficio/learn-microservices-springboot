package io.homo_efficio.lmsb.gamification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-14
 */
@Configuration
public class RabbitMQConfig implements RabbitListenerConfigurer {

    @Bean
    public TopicExchange multiplicationExchange(
            @Value(("${multiplication.exchange}")) String exchangeName) {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue gamificationMultiplicationQueue(
            @Value("${multiplication.queue}") String queueName) {
        return new Queue(queueName, true);
    }

    @Bean
    public Binding binding(TopicExchange topicExchange, Queue queue,
                           @Value("${multiplication.anything.routing-key}") String routingKey) {
        return BindingBuilder.bind(queue).to(topicExchange)
                .with(routingKey);
    }

    @Bean
    public MappingJackson2MessageConverter jackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory methodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(jackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(methodFactory());
    }
}
