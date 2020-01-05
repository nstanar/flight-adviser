package com.htec.user_management.user.repository;

import com.htec.user_management.user.repository.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nikola Stanar
 * <p>
 * Jpa repository for role.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds role by name.
     *
     * @param name Name of the role.
     * @return Role.
     */
    @Transactional(readOnly = true)
    Role findByName(final String name);

}
