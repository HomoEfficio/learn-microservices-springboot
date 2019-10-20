package io.homo_efficio.lmsb.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-20
 */
@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder,
                               @Value("${lmsb.upstream.url.multiplication}") String multiplicationURL,
                               @Value("${lmsb.upstream.url.gamification}") String gamificationURL) {
        return builder.routes()
                .route(p -> p.path("/multiplications/**")
                             .uri(multiplicationURL + "/multiplications"))
                .route(p -> p.path("/results/**")
                        .uri(multiplicationURL + "/results"))
                .route(p -> p.path("/leaders/**")
                        .uri(gamificationURL + "/leaders"))
                .route(p -> p.path("/stats/**")
                        .uri(gamificationURL + "/stats"))
                .build();
    }
}
