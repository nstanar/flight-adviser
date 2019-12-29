package com.htec.user_management.user.service.dto.converter;

import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.dto.UserDto;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link UserDto} <--> {@link User}.
 * @see DtoConverter
 */
public interface UserDtoConverter extends DtoConverter<UserDto, User> {

}
