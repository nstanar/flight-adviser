package com.htec.user_management.common.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Nikola Stanar
 * <p>
 * Jpa configuration.
 */
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.htec.user_management.user.repository"})
@EntityScan(basePackages = {
        "com.htec.user_management.user.repository.entity",
        "com.htec.domain_starter.repository.entity"
})
public class JpaConfiguration {

}
