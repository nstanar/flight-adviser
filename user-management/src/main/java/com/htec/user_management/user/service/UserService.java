package com.htec.user_management.user.service;

import com.htec.domain_starter.service.PagingAndSortingService;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.dto.RoleDto;
import com.htec.user_management.user.service.dto.UserDto;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over {@link UserDto}.
 */
public interface UserService extends PagingAndSortingService<UserDto, User> {

    @Override
    @PreAuthorize("isAnonymous() or hasRole('ADMIN')")
    UserDto createFrom(final @NotNull @Valid UserDto dto);

    /**
     * Updates user from dto content.
     *
     * @param id  Id of the dto.
     * @param dto DTO holding update content.
     * @return Updated user.
     * @see PagingAndSortingService#updateFrom(Long, BaseDto)
     */
    @Override
    @PostAuthorize("hasRole('ADMIN')")
    default UserDto updateFrom(final @NotNull Long id, final @NotNull @Valid UserDto dto) {
        return PagingAndSortingService.super.updateFrom(id, dto);
    }

    /**
     * Deletes user by id.
     *
     * @param id Id of the DTO.
     * @return Deleted user.
     * @see PagingAndSortingService#deleteById(Long)
     */
    @Override
    @PostAuthorize("hasRole('ADMIN')")
    UserDto deleteById(final Long id);

    /**
     * Finds user by username.
     *
     * @param username User's username.
     * @return Optional user.
     */
    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    Optional<UserDto> findByUsername(@NotBlank final String username);

    /**
     * Updates user of a given username.
     *
     * @param username User's username.
     * @param dto      Update content.
     * @return Updated user.
     */
    @PreAuthorize("hasRole('ADMIN') or #username==authentication.principal.username")
    UserDto updateByUsername(@NotBlank final String username, @NotNull @Valid UserDto dto);

    /**
     * Deletes user by username.
     *
     * @param username User's username.
     * @return Deleted user.
     */
    @PreAuthorize("hasRole('ADMIN') or #username==authentication.principal.username")
    UserDto deleteByUsername(@NotBlank final String username);

    /**
     * Finds user roles.
     *
     * @param userId Id ot the user.
     * @return User roles.
     */
    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    Set<RoleDto> findRolesBy(@NotNull final Long userId);

    /**
     * Assigns role to user.
     *
     * @param userId Id of the user.
     * @param role   Role.
     */
    @PreAuthorize("hasRole('ADMIN')")
    void assignRole(@NotNull final Long userId, @NotNull @Valid RoleDto role);
}
