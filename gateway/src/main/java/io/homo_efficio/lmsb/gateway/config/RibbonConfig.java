package io.homo_efficio.lmsb.gateway.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.context.annotation.Bean;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-25
 */
public class RibbonConfig {

    @Bean
    public IPing ribbonPing(IClientConfig config) {
        return new PingUrl(false, "/actuator/health");
    }

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new AvailabilityFilteringRule();
    }
}
