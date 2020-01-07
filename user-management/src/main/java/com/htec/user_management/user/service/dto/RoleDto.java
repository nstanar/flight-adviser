package com.htec.user_management.user.service.dto;

import com.htec.domain_starter.service.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Enum representing user role.
 */
@NoArgsConstructor
@Data
public class RoleDto extends BaseDto<Long> {

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
