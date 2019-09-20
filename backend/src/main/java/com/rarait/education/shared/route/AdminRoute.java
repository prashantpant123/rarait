package com.rarait.education.shared.route;

/**
 * @author Prajin Maharjan
 * @since Sep 05, 2018
 */
public final class AdminRoute extends BaseRoute {

    public static final String ADMIN_USER = ADMIN + "/user";
    public static final String ADMIN_USER_EDIT = ADMIN_USER + "/edit";
    public static final String ADMIN_USER_INFO = ADMIN_USER + "/{user_id}";

    public static final String CUSTOMER = ADMIN + "/customer";
    public static final String KYC = CUSTOMER + "/{customer_id}/customer";

    public static final String MERCHANT = ADMIN + "/merchant";
    public static final String MERCHANT_ID = MERCHANT + "/{merchant_id}";
    public static final String MERCHANT_DETAIL = MERCHANT_ID + "/detail";
    public static final String MERCHANT_CONTACT = MERCHANT_ID + "/contact";
    public static final String MERCHANT_CONTACT_ID = MERCHANT_CONTACT + "/{contact_id}";

    public static final String INSTITUTE = ADMIN + "/institute";
    public static final String INSTITUTE_EDIT = INSTITUTE + "/edit";
    public static final String INSTITUTE_STATUS = INSTITUTE + "/status";
    public static final String INSTITUTE_TYPE = INSTITUTE + "/type";
    public static final String INSTITUTE_DETAIL = INSTITUTE + "/{institute_id}/detail";
    public static final String INSTITUTE_FILE = INSTITUTE + "/file";
    public static final String INSTITUTE_FILE_ID = INSTITUTE + "/{institute_id}/file";
    public static final String INSTITUTE_REG_ID_CHECK = INSTITUTE + "/registration-check/{registration_id}";

    public static final String OCCUPATION = ADMIN + "/occupation";
    public static final String OCCUPATION_EDIT = OCCUPATION + "/edit";


    private AdminRoute() {
    }
}
