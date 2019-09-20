export class AdminApi {
  public static BASE_PATH = '/api/admin';
  public static ADMIN_USER = AdminApi.BASE_PATH + '/user';
  public static ADMIN_USER_EDIT = AdminApi.ADMIN_USER + '/edit';
  public static ADMIN_USER_INFO = AdminApi.ADMIN_USER + '/{user_id}';

  public static SCHOOL = AdminApi.BASE_PATH + '/institute';
  public static SCHOOL_EDIT_STATUS = AdminApi.SCHOOL + '/{institute_id}/detail';
  public static SCHOOL_TYPE = AdminApi.SCHOOL + '/type';
  public static SCHOOL_DETAIL = AdminApi.SCHOOL + '/{institute_id}/detail';
  public static SCHOOL_REGISTRATION_PREFIX_CHECK = AdminApi.SCHOOL + '/registration-check/{registration_id}';

  public static FILE = AdminApi.BASE_PATH + '/institute/file';
}
