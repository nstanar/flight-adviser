package com.htec.user_management.user.service;

import com.htec.domain_starter.service.CrudService;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.dto.RoleDto;
import com.htec.user_management.user.service.dto.UserDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over {@link UserDto}.
 */
public interface UserService extends CrudService<UserDto, User> {

    /**
     * Finds user by username.
     *
     * @param username User's username.
     * @return Optional user.
     */
    Optional<UserDto> findBy(@NotBlank final String username);

    /**
     * Finds user roles.
     *
     * @param userId Id ot the user.
     * @return User roles.
     */
    List<RoleDto> findRolesBy(@NotNull final Long userId);

}
