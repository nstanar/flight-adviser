package com.htec.user_management.user.service.validation.chain.impl;

import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.marker.Create;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import com.htec.user_management.user.service.dto.UserDto;
import com.htec.user_management.user.service.validation.PasswordMatcherValidator;
import com.htec.user_management.user.service.validation.UsernameUniquenessValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nikola Stanar
 * <p>
 * User business validator chain.
 */
@Component
@AllArgsConstructor
public class UserBusinessValidatorChainImpl implements BusinessValidatorChain<UserDto> {

    /**
     * Validates uniqueness of user's the username.
     */
    private final UsernameUniquenessValidator usernameUniquenessValidator;

    /**
     * Validates that user passwords(original and retyped) match.
     */
    private final PasswordMatcherValidator passwordMatcherValidator;

    /**
     * Decision cache.
     */
    protected final Map<Class<?>, List<BusinessValidator<UserDto>>> decisionCache = new HashMap<>();

    /**
     * Decouples validators.
     */
    @PostConstruct
    protected void decoupleValidators() {
        decisionCache.put(Create.class, List.of(usernameUniquenessValidator, passwordMatcherValidator));
    }

    /**
     * Gets decision cache.
     *
     * @return Decision cache.
     * @see BusinessValidatorChain#getDecisionCache()
     */
    @Override
    public Map<Class<?>, List<BusinessValidator<UserDto>>> getDecisionCache() {
        return decisionCache;
    }

}
