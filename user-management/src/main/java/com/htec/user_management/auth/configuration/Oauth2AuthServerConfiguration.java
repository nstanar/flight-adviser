package com.htec.user_management.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * @author Nikola Stanar
 * <p>
 * Auhtorization server configuration for oauth2.
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * Authentication manager.
     */
    private AuthenticationManager authenticationManager;

    /**
     * Data source.
     */
    private DataSource dataSource;

    /**
     * Password encoder.
     */
    private PasswordEncoder passwordEncoder;

    /**
     * User details service.
     */
    private UserDetailsService userDetailsService;

    /**
     * Setter for data source.
     *
     * @param dataSource Data source.
     */
    @Autowired
    public void setDataSource(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Setter for user details service.
     *
     * @param userDetailsService User details service.
     */
    @Autowired
    public void setUserDetailsService(final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Setter for authentication manager.
     *
     * @param authenticationManager Authentication manager.
     */
    @Autowired
    public void setAuthenticationManager(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Setter for password encoder.
     *
     * @param passwordEncoder Password encoder.
     */
    @Autowired
    public void setPasswordEncoder(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Configures oauth server.
     *
     * @param oauthServer Check {@link AuthorizationServerSecurityConfigurer}.
     */
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * Configures client details service.
     *
     * @param serviceConfigurer Check {@link ClientDetailsServiceConfigurer}.
     * @throws Exception Exception during configuration.
     */
    @Override
    public void configure(final ClientDetailsServiceConfigurer serviceConfigurer) throws Exception {
        serviceConfigurer.withClientDetails(clientDetailsService());
    }

    /**
     * Configures authorization server endpoints.
     *
     * @param endpoints Check {@link AuthorizationServerEndpointsConfigurer}.
     */
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager).tokenServices(tokenServices(providerManager()));
    }

    /**
     * Instantiates authorization server token services.
     *
     * @param providerManager Provider manager.
     * @return Check {@link AuthorizationServerTokenServices}.
     */
    @Bean
    @Primary
    public AuthorizationServerTokenServices tokenServices(final ProviderManager providerManager) {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setClientDetailsService(clientDetailsService());
        defaultTokenServices.setAuthenticationManager(providerManager);
        return defaultTokenServices;
    }

    /**
     * Instantiates provider manager.
     *
     * @return Provider manager.
     */
    @Bean
    public ProviderManager providerManager() {
        final PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(userDetailsService));
        return new ProviderManager(Collections.singletonList(provider));
    }

    /**
     * Instantiates token store.
     *
     * @return Token store.
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * Instantiates client details service.
     *
     * @return Client details service.
     */
    @Bean
    public ClientDetailsService clientDetailsService() {
        final JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }
}
