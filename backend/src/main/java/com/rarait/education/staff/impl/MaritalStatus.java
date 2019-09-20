package com.rarait.education.staff.impl;

import com.rarait.education.utility.bloodgroup.BloodGroup;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public enum MaritalStatus implements Serializable {

    SINGLE(1), MARRIED(2), DIVORCED(3), WIDOWED(4);

    private int value;

    MaritalStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MaritalStatus getEnum(int value) {
        Optional<MaritalStatus> group = Arrays.stream(values())
                .filter(type -> type.getValue() == value)
                .findFirst();
        return group.orElseThrow(() -> new IllegalArgumentException("Marital status not found"));
    }
}
