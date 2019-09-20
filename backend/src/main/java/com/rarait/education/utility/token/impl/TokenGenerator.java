package com.rarait.education.utility.token.impl;

import com.rarait.education.utility.properties.PropertyType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class TokenGenerator {

    public static String getAlphaNumeric(int tokenLength){
        String random = RandomStringUtils.randomAlphanumeric(tokenLength).toUpperCase();
        if (StringUtils.isAlpha(random)) {
            random = getAlphaNumeric(tokenLength);
        }
        return random;
    }

    public static String getNumeric() {
        return RandomStringUtils.randomNumeric(Integer.parseInt(PropertyType.TOKEN_LENGTH.getValue()));
    }
}