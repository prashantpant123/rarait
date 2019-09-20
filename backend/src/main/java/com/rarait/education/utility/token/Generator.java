package com.rarait.education.utility.token;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class Generator {

    public static String getRandomAlphanumeric(int length) {
        String random = RandomStringUtils.randomAlphanumeric(length);
        if (StringUtils.isAlpha(random)) {
            random = getRandomAlphanumeric(length);
        }
        return random;
    }

}
