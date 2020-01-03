package com.htec.user_management.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

/**
 * @author Nikola Stanar
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
//TODO: document this.
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Bean
    public MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .requestMatchers()
                .antMatchers("/users/**", "/actuator/**")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }

}
