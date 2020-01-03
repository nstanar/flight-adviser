package com.htec.user_management.user.repository;

import com.htec.user_management.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Repository for {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds user by username.
     *
     * @param username Username.
     * @return Optional user.
     */
    @Transactional(readOnly = true)
    Optional<User> findByUsername(final String username);

    /**
     * Deletes user by username.
     *
     * @param username User's username.
     */
    void deleteByUsername(final String username);

}
