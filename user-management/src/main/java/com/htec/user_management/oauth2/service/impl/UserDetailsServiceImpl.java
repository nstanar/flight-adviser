package com.htec.user_management.oauth2.service.impl;

import com.htec.user_management.user.repository.UserRepository;
import com.htec.user_management.user.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.htec.user_management.common.constants.MessageSourceKeys.INVALID_CREDENTIALS;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

/**
 * @author Nikola Stanaer
 * <p>
 * Service for loading user details.
 * @see UserDetailsService#loadUserByUsername(String)
 */
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Jpa repository for user.
     */
    private final UserRepository userRepository;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Loads user by username.
     *
     * @param username User's username.
     * @return Check {@link UserDetails}.
     * @throws UsernameNotFoundException if user does not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            log.info("Authenticating user with username {}", username);
            final String message = messageSource.getMessage(INVALID_CREDENTIALS, new Object[]{}, getLocale());
            throw new UsernameNotFoundException(message);
        }
        final User user = optionalUser.get();
        Hibernate.initialize(user.getRoles());
        return user;
    }

}
