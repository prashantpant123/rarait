package com.rarait.education.login.impl;

import com.rarait.education.login.*;
import com.rarait.education.login.resource.UserCreateRequest;
import com.rarait.education.utility.properties.ApplicationPropertyService;
import com.rarait.education.utility.token.spi.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class InstituteUserServiceImpl extends UserServiceImpl implements InstituteUserService {

    @Autowired
    public InstituteUserServiceImpl(UserRepository userRepository,
                                    UserRoleRepository userRoleRepository,
                                    RoleRepository roleRepository,
                                    PasswordEncoder passwordEncoder,
                                    UserValidator userValidator,
                                    ChangePasswordValidator changePasswordValidator,
                                    ApplicationPropertyService applicationPropertyService,
                                    TokenProvider tokenProvider) {
        super(userRepository,
                userRoleRepository,
                roleRepository,
                passwordEncoder,
                userValidator,
                changePasswordValidator,
                applicationPropertyService,
                tokenProvider);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User addCredential(UserCreateRequest request) {
        return addUserLogin(request);
    }

}
