package com.rarait.education.utility.bloodgroup;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public enum BloodGroup implements Serializable {

    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    O_NEGATIVE("O-"),
    O_POSITIVE("O+"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-");

    private String value;

    BloodGroup(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BloodGroup getEnum(String value) {
        Optional<BloodGroup> group = Arrays.stream(values())
                .filter(type -> type.value.equals(value))
                .findFirst();
        return group.orElseThrow(() -> new IllegalArgumentException("Blood group not found"));
    }

    public static BloodGroup getEnumFromOrdinal(int ordinal) {
        Optional<BloodGroup> group = Arrays.stream(values())
                .filter(type -> type.ordinal() == ordinal)
                .findFirst();
        return group.orElseThrow(() -> new IllegalArgumentException("Blood group not found"));
    }
}
