package com.htec.user_management.user.service.dto;

import com.htec.domain_starter.service.dto.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nikola Stanar
 * <p>
 * Enum representing user role.
 */
@NoArgsConstructor
@Data
public class RoleDto extends BaseDto {

    /**
     * Role name.
     */
    private String name;

}
