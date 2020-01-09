package com.htec.flight_management.common.configuration;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

/**
 * @author Nikola Stanar
 * <p>
 * Resource server configuration for oauth2.
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * Instantiates expression handler.
     *
     * @return Method security expression handler.
     */
    @Bean
    public MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }

    /**
     * Configures http security.
     *
     * @param http Check {@link HttpSecurity}.
     * @throws Exception Exception during configuration.
     */
    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/cities", "/comments", "/airports", "/flights").denyAll()
                .antMatchers(HttpMethod.GET, "/cities", "/comments", "/airports", "/flights").denyAll()
                .antMatchers("/import").hasRole("ADMIN")
                .antMatchers("/actuator**").hasRole("ADMIN");
    }

    /**
     * Instantiates resource server token services.
     *
     * @return Resource server token services.
     */
    @Bean
    @ConfigurationProperties("resource.server.token.services")
    public ResourceServerTokenServices remoteTokenServices() {
        return new RemoteTokenServices();
    }

    /**
     * Instantiates authentication manager.
     *
     * @param resourceServerTokenServices Resource server token services.
     * @return Authentication manager.
     */
    @Bean
    public AuthenticationManager authenticationManager(final ResourceServerTokenServices resourceServerTokenServices) {
        final OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        authenticationManager.setTokenServices(resourceServerTokenServices);
        return authenticationManager;
    }

}