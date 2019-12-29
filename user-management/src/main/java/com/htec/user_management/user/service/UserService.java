package com.htec.user_management.user.service;

import com.htec.user_management.user.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over {@link UserDto}.
 */
public interface UserService {

    /**
     * Finds paged collection of users.
     *
     * @param pageable Check {@link Pageable}.
     * @return Paged collection of users.
     */
    Page<UserDto> findAll(@NotNull final Pageable pageable);

    /**
     * Finds user by id.
     *
     * @param id User's id.
     * @return Optional user having specific id.
     */
    Optional<UserDto> findBy(@NotNull final Long id);

    /**
     * Creates user.
     *
     * @param user User that is about to be created.
     * @return Created user.
     */
    UserDto create(@NotNull @Valid final UserDto user);

}
