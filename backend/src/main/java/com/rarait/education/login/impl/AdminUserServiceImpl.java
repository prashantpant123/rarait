package com.rarait.education.login.impl;

import com.rarait.education.login.*;
import com.rarait.education.login.resource.UserCreateRequest;
import com.rarait.education.login.resource.UserResponse;
import com.rarait.education.login.resource.UserStatusUpdateRequest;
import com.rarait.education.utility.properties.ApplicationPropertyService;
import com.rarait.education.utility.token.spi.TokenProvider;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.security.impl.UserStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class AdminUserServiceImpl extends UserServiceImpl implements AdminUserService {

    @Autowired
    public AdminUserServiceImpl(
            UserRepository userRepository,
            UserRoleRepository userRoleRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            UserValidator userValidator,
            ChangePasswordValidator changePasswordValidator,
            ApplicationPropertyService applicationPropertyService,
            TokenProvider tokenProvider
    ) {
        super(
                userRepository,
                userRoleRepository,
                roleRepository,
                passwordEncoder,
                userValidator,
                changePasswordValidator,
                applicationPropertyService,
                tokenProvider
        );
    }

    @Override
    @Transactional
    public List<UserResponse> getAllUsers() {
        List<User> users = userRoleRepository.findAllByRole(RoleName.ROLE_ADMIN);
        return UserConvert.convertAll(users);
    }

    @Override
    public void updateUserStatus(UserStatusUpdateRequest request) {
        Optional<User> users = userRepository.findById(request.getId());
        User user = users.orElseThrow(() -> new ClientRestException("User id not found"));
        user.setStatus(UserStatus.valueOf(request.getStatus()));
        userRepository.save(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User addCredential(UserCreateRequest request) {
        return addUserLogin(request);
    }

    @Override
    public UserResponse findUserInfo(long userId) {
        Optional<User> users = userRepository.findById(userId);
        User user = users.orElseThrow(()-> new ClientRestException("User not found"));
        List<Role> roles =userRoleRepository.findAllByUser(user.getId());
        return UserConvert.convert(user,roles);
    }
}
