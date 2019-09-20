package com.rarait.education.login;

import com.rarait.education.login.impl.RoleName;
import com.rarait.education.login.impl.UserType;
import com.rarait.framework.repository.BaseJpaRepository;
import com.rarait.education.login.impl.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since Aug 24, 2018
 */
public interface UserRepository extends BaseJpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("Select u FROM User u WHERE u.username=:username")
    User getByUsername(@Param("username") String username);
}