package com.rarait.education.shared.route;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class UtilityRoute extends BaseRoute {

    public static final String ADDRESS = UTILITY + "/address";
    public static final String ADDRESS_STATE = ADDRESS + "/state";
    public static final String ADDRESS_DISTRICT = ADDRESS_STATE + "/{state_id}/district";
    public static final String ADDRESS_MUNICIPALITY = ADDRESS_STATE + "/district/{district_id}/municipality";

    public static final String OCCUPATION = UTILITY + "/occupation";

    public static final String NATIONALITY = UTILITY + "/nationality";
    public static final String BLOOD_GROUP = UTILITY + "/blood_group";
    public static final String MARITAL_STATUS = UTILITY + "/marital_status";

    private UtilityRoute() {
    }
}
