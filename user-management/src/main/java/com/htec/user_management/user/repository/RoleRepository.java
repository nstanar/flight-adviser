package com.htec.user_management.user.repository;

import com.htec.user_management.user.repository.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nikola Stanar
 * <p>
 * Jpa repository for role.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    /**
     * Finds role by name.
     *
     * @param name Name of the role.
     * @return Role.
     */
    @Transactional(readOnly = true)
    Role findByName(final String name);

}
