export class UtilityApi {

  private static UTILITY = '/api/utility';

  public static ADDRESS_STATE = UtilityApi.UTILITY + '/address/state';
  public static ADDRESS_DISTRICT = UtilityApi.UTILITY + '/address/state/{state_id}/district';
  public static ADDRESS_MUNICIPALITY = '/api/utility/address/state/district/{district_id}/municipality';

  public static OCCUPATION = UtilityApi.UTILITY + '/occupation';
  public static NATIONALITY = UtilityApi.UTILITY + '/nationality';
  public static BLOOD_GROUP = UtilityApi.UTILITY + '/blood_group';
  public static MARITAL_STATUS = UtilityApi.UTILITY + '/marital_status';
}
