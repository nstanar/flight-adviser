package com.htec.user_management.oauth2.controller.impl;

import com.htec.user_management.auth.service.RevokeTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nikola Stanar
 * <p>
 * Oauth2 controller.
 */
@RestController
@RequestMapping("/oauth")
@AllArgsConstructor
public class OAuth2ControllerImpl {

    /**
     * Revoke token service.
     */
    private final RevokeTokenService revokeTokenService;

    /**
     * Logs me out from my profile.
     *
     * @param authentication Check authentication.
     * @return 204
     */
    @DeleteMapping("/me")
    public ResponseEntity<Void> logMeOut(final Authentication authentication) {
        revokeTokenService.revokeFor(authentication.getName());
        return ResponseEntity.noContent().build();
    }

}
