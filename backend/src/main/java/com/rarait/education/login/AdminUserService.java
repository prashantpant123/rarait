package com.rarait.education.login;

import com.rarait.education.login.resource.UserResponse;
import com.rarait.education.login.resource.UserStatusUpdateRequest;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface AdminUserService extends UserService {

    List<UserResponse> getAllUsers();

    void updateUserStatus(UserStatusUpdateRequest request);

    UserResponse findUserInfo(long userId);
}
