package com.rarait.education.shared;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public enum Gender implements Serializable {

    MALE(1), FEMALE(2);

    private int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Optional<Gender> valueOf(int value) {
        return Arrays.stream(values())
                .filter(gender -> gender.value == value)
                .findFirst();
    }
}
