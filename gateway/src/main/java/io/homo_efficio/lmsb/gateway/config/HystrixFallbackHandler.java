package io.homo_efficio.lmsb.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-27
 */
@Component
@Slf4j
public class HystrixFallbackHandler {

    public Mono<ServerResponse> get(ServerRequest request) {
        log.info("reqeust: {}", request);
        Mono<String> hystrixFallbackMono = Mono.just("HystrixFallback");
        return ServerResponse.ok().body(hystrixFallbackMono, String.class);
    }
}
