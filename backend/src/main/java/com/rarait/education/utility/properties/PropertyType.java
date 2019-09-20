package com.rarait.education.utility.properties;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public enum PropertyType implements Serializable{

    PASSWORD_REGEX("[a-zA-Z0-9]{6,30}"),
    TOKEN_LENGTH("5"),
    TOKEN_EXPIRY_TIME_MINUTE("30");

    private String value;

    private PropertyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
