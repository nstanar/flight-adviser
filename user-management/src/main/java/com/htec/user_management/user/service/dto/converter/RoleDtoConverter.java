package com.htec.user_management.user.service.dto.converter;

import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.user_management.user.repository.entity.Role;
import com.htec.user_management.user.service.dto.RoleDto;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link RoleDto} to {@link Role} and vice-versa.
 */
public interface RoleDtoConverter extends DtoConverter<RoleDto, Role> {

}
