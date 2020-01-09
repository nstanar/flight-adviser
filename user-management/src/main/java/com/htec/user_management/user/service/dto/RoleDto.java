package com.htec.user_management.user.service.dto;

import com.htec.domain_starter.service.dto.BaseDto;
import lombok.*;

import javax.validation.constraints.NotNull;


/**
 * @author Nikola Stanar
 * <p>
 * Enum representing user role.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoleDto extends BaseDto {

    /**
     * Role name.
     */
    @NotNull
    private Value value;

    /**
     * Role values.
     */
    @AllArgsConstructor
    @Getter
    public enum Value {

        ROLE_ADMIN("ROLE_ADMIN"), ROLE_REGULAR_USER("ROLE_REGULAR_USER");

        /**
         * Name of the role.
         */
        private final String name;

    }

}
