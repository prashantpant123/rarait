package com.rarait.education.login.impl;

import com.rarait.education.login.*;
import com.rarait.education.login.resource.UserCreateRequest;
import com.rarait.education.utility.properties.ApplicationPropertyService;
import com.rarait.education.utility.token.spi.TokenProvider;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.security.auth.AuthSessionUtil;
import com.rarait.framework.security.impl.UserStatus;
import com.rarait.framework.security.resource.ChangePasswordRequest;
import com.rarait.framework.security.resource.RegistrationValidationRequest;
import com.rarait.framework.shared.DateUtil;
import com.rarait.framework.shared.Status;
import com.rarait.education.utility.properties.PropertyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
//@Service
@Slf4j
public class UserServiceImpl {

    protected final UserRepository userRepository;
    protected final UserRoleRepository userRoleRepository;
    protected final RoleRepository roleRepository;
    protected final PasswordEncoder passwordEncoder;
    protected final UserValidator userValidator;
    protected final ChangePasswordValidator changePasswordValidator;
    protected final ApplicationPropertyService applicationPropertyService;
    protected final TokenProvider tokenProvider;

    public UserServiceImpl(
            UserRepository userRepository,
            UserRoleRepository userRoleRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            UserValidator userValidator,
            ChangePasswordValidator changePasswordValidator,
            ApplicationPropertyService applicationPropertyService,
            TokenProvider tokenProvider
    ) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.changePasswordValidator = changePasswordValidator;
        this.userValidator = userValidator;
        this.applicationPropertyService = applicationPropertyService;
        this.tokenProvider = tokenProvider;
    }

    public User findByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    protected User addUserLogin(final UserCreateRequest request) {
        userValidator.validate(request);

        Optional<User> users = userRepository.findByUsername(request.getUsername());
        if (users.isPresent()) {
            throw new ClientRestException("Username " + request.getUsername() + " already exists");
        }

        User user = new User();
        user.setAttempt((short) 0);
        user.setLastLogin(new Date());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(String.valueOf(request.getPassword())));

        if (request.getRoleNames().equals(RoleName.ROLE_PARENT) || request.getRoleNames().equals(RoleName.ROLE_STUDENT)) {
            int expiryMinute = Integer.valueOf(applicationPropertyService.getActiveValueByType(
                    PropertyType.TOKEN_EXPIRY_TIME_MINUTE));
            user.setToken(tokenProvider.getAlphaNumeric(
                    applicationPropertyService.getActiveValueByType(PropertyType.TOKEN_LENGTH)));
            user.setTokenExpiry(DateUtil.addMinuteOnCurrent(expiryMinute));
            user.setStatus(UserStatus.INACTIVE);
        } else {
            user.setStatus(UserStatus.ACTIVE);
        }
        user = userRepository.save(user);

        for (RoleName roleName : request.getRoleNames()) {
            UserRole ur = new UserRole();
            ur.setRole(roleRepository.findByName(roleName));
            ur.setUser(user);
            userRoleRepository.save(ur);
        }
        return user;
    }

    public User verifyRegistration(final RegistrationValidationRequest request) {
        Optional<User> users = userRepository.findByUsername(request.getUsername());
        User user = users.orElseThrow(() -> new ClientRestException("Username " +
                request.getUsername() + " not found"));
        if (!Status.INACTIVE.equals(user.getStatus())) {
            throw new ClientRestException("User cannot be verified.");
        } else if (!request.getToken().equals(
                user.getToken()) || new Date().getTime() > user.getTokenExpiry().getTime()) {
            throw new ClientRestException("Invalid verification token or expired");
        }
        user.setToken(null);
        user.setStatus(UserStatus.ACTIVE.ACTIVE);
        return userRepository.save(user);
    }

    public void changePassword(final ChangePasswordRequest request) {
        Optional<User> users = userRepository.findByUsername(
                AuthSessionUtil.getCurrentUser().getUsername());
        User user = users.orElseThrow(() -> new ClientRestException("Username " +
                request.getUsername() + " doesn't exists"));
        changePasswordValidator.validate(request);
        user.setPassword(passwordEncoder.encode(String.valueOf(request.getPassword())));
        userRepository.save(user);
    }
}