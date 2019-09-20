package com.rarait.education.staff.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public enum EmployeeType implements Serializable {

    TEACHER(2),
    ACCOUNTANT(4),
    ADMINISTRATION(3),
    PRINCIPAL(1);

    private int value;

    EmployeeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Optional<EmployeeType> valueOf(int value) {
        return Arrays.stream(values())
                .filter(type -> type.value == value)
                .findFirst();
    }
}
