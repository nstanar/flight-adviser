package com.htec.flight_management.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * @author Nikola Stanar
 * <p>
 * Web configuration.
 */
@Configuration
public class HttpClientConfiguration {

    /**
     * Instantiates rest template.
     *
     * @return Rest template.
     */
    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }

}
