package io.homo_efficio.lmsb.gamification.client;

import io.homo_efficio.lmsb.gamification.client.dto.MultiplicationAttempt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-16
 */
@Component
public class MultiplicationAttemptClientImpl implements MultiplicationAttemptClient {

    private final RestTemplate restTemplate;
    private final String multiplicationHost;

    @Autowired
    public MultiplicationAttemptClientImpl(RestTemplate restTemplate,
                                           @Value("${multiplicationHost}") String multiplicationHost) {
        this.restTemplate = restTemplate;
        this.multiplicationHost = multiplicationHost;
    }

    @Override
    public MultiplicationAttempt retrieveMultiplicationAttemptById(Long attemptId) {
        return restTemplate.getForObject(multiplicationHost + "/results/" + attemptId, MultiplicationAttempt.class);
    }
}
