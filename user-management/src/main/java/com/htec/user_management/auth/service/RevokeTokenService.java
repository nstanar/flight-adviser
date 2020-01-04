package com.htec.user_management.auth.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author Nikola Stanar
 * <p>
 * Service for revocatiion of tokens.
 */
@Validated
@FunctionalInterface
public interface RevokeTokenService {

    /**
     * Revokes all tokens for given username.
     *
     * @param username Username.
     */
    @PostAuthorize("hasRole('ADMIN') or #username==authentication.principal.name")
    void revokeFor(@NotBlank final String username);
}
