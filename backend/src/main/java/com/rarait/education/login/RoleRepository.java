package com.rarait.education.login;

import com.rarait.education.login.impl.Role;
import com.rarait.education.login.impl.RoleName;
import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface RoleRepository extends BaseJpaRepository<Role, Short> {

    @Query("SELECT r FROM Role r WHERE r.name= :roleName")
    Role findByName(@Param("roleName") RoleName role);
}
