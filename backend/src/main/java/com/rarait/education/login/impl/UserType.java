package com.rarait.education.login.impl;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Deprecated
public enum UserType implements Serializable{

    ADMIN,
    INSTITUTE_ADMIN,
    TEACHER,
    PARENT,
    STUDENT;
}
