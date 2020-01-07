package com.htec.domain_starter.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Persistence configuration.
 */
@Configuration
public class AuditConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            final String result;
            final SecurityContext context = SecurityContextHolder.getContext();
            final Authentication authentication = context.getAuthentication();
            final Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                result = ((UserDetails) principal).getUsername();
            } else if (principal instanceof ClientDetails) {
                result = ((ClientDetails) principal).getClientId();
            } else {
                result = (String) principal;
            }

            return Optional.ofNullable(result);
        };
    }

}
