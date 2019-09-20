package com.rarait.education.login.impl;

import com.rarait.education.login.resource.UserResponse;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class UserConvert {

    private UserConvert() {
    }

    public static List<UserResponse> convertAll(List<User> users) {
        return users.stream()
                .map(UserConvert::convert)
                .collect(Collectors.toList());
    }

    public static UserResponse convert(User user) {
        return convert(user, null);
    }

    public static UserResponse convert(User user, List<Role> roles) {
        String userRole = null;
        if(roles !=null) {
            for (Role role : roles) {
                userRole = (userRole == null ? role.getName().toString() : userRole + "," + role.getName().toString());
            }
        }
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .lastLoginDate(user.getLastLogin())
                .status(user.getStatus().name())
                .role(userRole)
                .build();
    }
}
