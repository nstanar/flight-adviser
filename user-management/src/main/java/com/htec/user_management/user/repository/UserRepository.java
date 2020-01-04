package com.htec.user_management.user.repository;

import com.htec.user_management.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Repository for {@link User}.
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds user by username (ignores case).
     *
     * @param username Username.
     * @return Optional user.
     */
    @Transactional(readOnly = true)
    Optional<User> findByUsernameIgnoreCase(final String username);

    /**
     * Deletes user by username.
     *
     * @param username User's username.
     */
    @Modifying
    void deleteByUsername(final String username);

    /**
     * Checks if user with a given username already exists (ignores case).
     *
     * @param username User's username.
     * @return True if exists, false otherwise.
     */
    @Transactional(readOnly = true)
    boolean existsByUsernameIgnoreCase(final String username);

}
