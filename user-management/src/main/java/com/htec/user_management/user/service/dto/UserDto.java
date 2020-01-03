package com.htec.user_management.user.service.dto;

import com.htec.domain_starter.service.dto.AuditAwareDto;
import com.htec.domain_starter.service.dto.BaseDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Nikola Stanar
 * <p>
 * DTO class representing user.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"password"})
@EqualsAndHashCode(exclude = {"password"})
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
    //TODO: apply pattern for password
    private char[] password;

}
