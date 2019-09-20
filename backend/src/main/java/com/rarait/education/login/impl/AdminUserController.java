package com.rarait.education.login.impl;

import com.rarait.education.login.AdminUserService;
import com.rarait.education.login.resource.UserCreateRequest;
import com.rarait.education.login.resource.UserResponse;
import com.rarait.education.login.resource.UserStatusUpdateRequest;
import com.rarait.education.shared.route.AdminRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@RestController
public class AdminUserController {

    private AdminUserService adminUserService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(AdminRoute.ADMIN_USER)
    public void addUser(@RequestBody UserCreateRequest request) {
        request.setRoleNames(Arrays.asList(RoleName.ROLE_ADMIN));
        adminUserService.addCredential(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(AdminRoute.ADMIN_USER)
    public List<UserResponse> findAll() {
        return adminUserService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(AdminRoute.ADMIN_USER_EDIT)
    public void updateUserStatus(@RequestBody UserStatusUpdateRequest request) {
        adminUserService.updateUserStatus(request);
    }

    @GetMapping(value = AdminRoute.ADMIN_USER_INFO)
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserDetail(
            @PathVariable("user_id") Long userId) {
        return adminUserService.findUserInfo(userId);
    }
}
