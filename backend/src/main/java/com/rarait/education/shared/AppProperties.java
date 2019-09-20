package com.rarait.education.shared;

import com.rarait.framework.shared.BaseProperty;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class AppProperties extends BaseProperty {

//        public static final String PROP_PATH = APP_PROP + "education_prop.yml";
    public static final String PROP_PATH = "application.yml";
    public static final String TOKEN_EXPIRATION = "${jwt.expiration}";
    public static final String SECRET = "${jwt.secret}";
    public static final String DOMAIN_URL="${domain-url}";

    private AppProperties() {
    }
}
