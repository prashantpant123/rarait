package com.rarait.education.attendance;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public enum AttendanceStatus implements Serializable {

    PRESENT(1),
    ABSENT(2),
    LATE(3);

    private int value;

    AttendanceStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static AttendanceStatus getEnum(int name) {
        for (AttendanceStatus v : values()) {
            if (v.value == name) {
                return v;
            }
        }
        return null;
    }
}
