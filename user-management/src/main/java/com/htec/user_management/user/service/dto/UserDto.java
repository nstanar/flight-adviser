package com.htec.user_management.user.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.htec.domain_starter.service.dto.AuditAwareDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Nikola Stanar
 * <p>
 * DTO class representing user.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"password", "retypedPassword"})
public class UserDto extends AuditAwareDto {

    /**
     * User's first name.
     */
    @NotBlank
    @Size(min = 1, max = 50)
    private String firstName;

    /**
     * User's last name.
     */
    @NotBlank
    @Size(min = 1, max = 50)
    private String lastName;

    /**
     * User's username.
     */
    @NotBlank
    @Size(min = 5, max = 50)
    private String username;

    /**
     * User's password.
     */
    @NotEmpty
    @Size(min = 6, max = 255)
    private char[] password;

    /**
     * User's retyped password.
     */
    @NotEmpty
    @Size(min = 6, max = 255)
    private char[] retypedPassword;

    /**
     * Default role of the user.
     */
    @JsonIgnore
    private RoleDto defaultRole;

}
