package com.rarait.education.structure.department.impl;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public enum DepartmentType implements Serializable {

    KINDERGARTEN("Kindergarten"),
    PRIMARY("Primary Level"),
    SECONDARY("Secondary Level"),
    POST_SECONDARY("Post Secondary Level"),
    HIGHER_SECONDARY("Higher Secondary"),
    SCIENCE("Science"),
    MANAGEMENT("Management"),
    HUMANITIES("Humanities");

    private String value;

    DepartmentType(String value) {
        this.value = value;
    }

    public boolean equalsName(String otherName) {
        return value.equals(otherName);
    }

    public String toString() {
        return this.value;
    }

    public static DepartmentType getEnum(String name) {
        for (DepartmentType v : values()) {
            if (v.equalsName(name)) {
                return v;
            }
        }
        return null;
    }

    public static DepartmentType getEnum(int ordinal) {
        for (DepartmentType v : values()) {
            if (v.ordinal() == ordinal) {
                return v;
            }
        }
        return null;
    }
}
