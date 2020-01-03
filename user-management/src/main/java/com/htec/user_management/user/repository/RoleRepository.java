package com.htec.user_management.user.repository;

import com.htec.user_management.user.repository.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Jpa repository for role.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    /**
     * Finds user roles.
     *
     * @param userId Id of the user.
     * @return Roles of the user.
     */
    @Transactional(readOnly = true)
    List<Role> findAllByUserId(final Long userId);

}
