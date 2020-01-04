package com.htec.user_management.oauth2.service.impl;

import com.htec.user_management.auth.service.RevokeTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.Collection;

/**
 * @author Nikola Stanar
 * <p>
 * Service for token revocation.
 * @see RevokeTokenService
 */
@Service
@Slf4j
@AllArgsConstructor
public class RevokeTokenServiceImpl implements RevokeTokenService {

    /**
     * Consumer token services.
     */
    private final ConsumerTokenServices consumerTokenServices;

    /**
     * Token store.
     */
    private final JdbcTokenStore jdbcTokenStore;

    /**
     * Revokes all tokens for given username.
     *
     * @param username Username.
     * @see RevokeTokenService#revokeFor(String)
     */
    @Override
    public void revokeFor(final @NotBlank String username) {
        final Collection<OAuth2AccessToken> tokens = jdbcTokenStore.findTokensByUserName(username);
        tokens.forEach(oAuth2AccessToken -> consumerTokenServices.revokeToken(oAuth2AccessToken.getValue()));
    }
}
