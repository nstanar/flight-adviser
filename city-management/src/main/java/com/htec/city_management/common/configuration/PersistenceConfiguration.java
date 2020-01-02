package com.htec.city_management.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Persistence configuration.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
//TODO: extract this and make it work with session context after auth and resource server are made.
public class PersistenceConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("unknown");
    }
}
