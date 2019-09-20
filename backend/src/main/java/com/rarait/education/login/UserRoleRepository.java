package com.rarait.education.login;

import com.rarait.education.login.impl.Role;
import com.rarait.education.login.impl.RoleName;
import com.rarait.education.login.impl.User;
import com.rarait.education.login.impl.UserRole;
import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface UserRoleRepository extends BaseJpaRepository<UserRole, Long> {

    @Query("SELECT ur.role FROM UserRole ur WHERE ur.user.id=?1")
    List<Role> findAllByUser(long userId);

    @Query("SELECT ur.user FROM UserRole ur WHERE ur.role.name = :role")
    List<User> findAllByRole(@Param("role") RoleName roleName);


}
