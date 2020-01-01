package com.htec.user_management.user.service;

import com.htec.domain_starter.service.CrudService;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.dto.UserDto;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over {@link UserDto}.
 */
public interface UserService extends CrudService<UserDto, User> {

}
