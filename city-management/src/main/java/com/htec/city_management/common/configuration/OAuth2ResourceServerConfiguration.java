package com.htec.city_management.common.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
//TODO: document this.
@AllArgsConstructor
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
                .antMatchers("/countries/**", "/cities/**", "/comments", "/actuator**")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }

    @Bean
    @ConfigurationProperties("resource.server.token.services")
    public ResourceServerTokenServices remoteTokenServices() {
        return new RemoteTokenServices();
    }

    @Bean
    public AuthenticationManager authenticationManager(final ResourceServerTokenServices resourceServerTokenServices) {
        final OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        authenticationManager.setTokenServices(resourceServerTokenServices);
        return authenticationManager;
    }

}