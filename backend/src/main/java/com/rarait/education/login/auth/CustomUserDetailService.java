package com.rarait.education.login.auth;

import com.rarait.education.login.UserRoleRepository;
import com.rarait.education.login.impl.Role;
import com.rarait.framework.security.impl.DefaultUserDetails;
import com.rarait.education.login.UserRepository;
import com.rarait.education.login.impl.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Component
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository,
                               UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> users = userRepository.findByUsername(username);
        User user = users.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        List<Role> roles = userRoleRepository.findAllByUser(user.getId());
        String userRole = null;
        for (Role role : roles) {
            userRole = (userRole == null ? role.getName().toString() : userRole + "," + role.getName().toString());
        }
        return new DefaultUserDetails(user, AuthorityUtils.createAuthorityList(userRole));
    }
}