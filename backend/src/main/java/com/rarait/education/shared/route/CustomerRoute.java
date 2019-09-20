package com.rarait.education.shared.route;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class CustomerRoute extends BaseRoute{

    private final static String NOTIFICATION = CUSTOMER + "/notification";
    public final static String EMAIL = NOTIFICATION + "/email";
    public final static String REGISTER = "/api" + "/register";
    public final static String REGISTER_VERIFY = REGISTER + "/verify";
    public final static String KYC = CUSTOMER + "/kyc";
    public final static String STATEMENT = CUSTOMER + "/statement";

    private CustomerRoute() {
    }
}
