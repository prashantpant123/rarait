
export class ClientApi {

  public static BASE = '/api/education';

  public static SUMMARY = ClientApi.BASE + '/summary';

  public static SCHOOL = ClientApi.BASE + '/info';

  public static STUDENT = ClientApi.BASE + '/student';
  public static STUDENT_EDIT = ClientApi.STUDENT + '/edit';
  public static STUDENT_ID = ClientApi.STUDENT + '/{student_id}';
  public static STUDENT_SEARCH = ClientApi.STUDENT + '/search';
  public static STUDENT_REGISTRATION_CHECK = ClientApi.STUDENT + '/registration/{registration_number}/check';

  public static SUBJECT = ClientApi.BASE + '/subject';
  public static SUBJECT_EDIT = ClientApi.SUBJECT + '/edit';
  public static SUBJECT_ID = ClientApi.SUBJECT + '/{subject_id}';
  public static SUBJECT_CLASS = ClientApi.SUBJECT + '/class/{class_id}';

  public static CLASS = ClientApi.BASE + '/level';
  public static CLASS_ID = ClientApi.CLASS + '/{class_id}';
  public static CLASS_EDIT = ClientApi.CLASS + '/edit';
  public static CLASS_LIST = ClientApi.CLASS + '/list';

  public static DEPARTMENT = ClientApi.BASE + '/department';
  public static DEPARTMENT_LIST = ClientApi.DEPARTMENT + '/list';

  public static SECTION = ClientApi.CLASS + '/section';
  public static SECTION_ID = ClientApi.SECTION + '/{section_id}';
  public static SECTION_LIST = ClientApi.SECTION + '/list';
  public static SECTION_DROPDOWN = ClientApi.CLASS + '/{class_id}/section/list';

  public static ACADEMIC_SESSION = ClientApi.BASE + '/academic-session';
  public static ACADEMIC_SESSION_ID = ClientApi.ACADEMIC_SESSION + '/{academic_session_id}';
  public static ACADEMIC_SESSION_EDIT = ClientApi.ACADEMIC_SESSION + '/edit';

  public static STAFF = ClientApi.BASE + '/employee';
  public static STAFF_ID = ClientApi.STAFF + '/{staff_id}';
  public static STAFF_EDIT = ClientApi.STAFF + '/edit';
  public static STAFF_TYPE = ClientApi.STAFF + '/type';

  public static EXAM = ClientApi.BASE + '/exam';
  public static EXAM_ID = ClientApi.EXAM + '/{exam_id}';
  public static EXAM_EDIT = ClientApi.EXAM + '/edit';
  public static EXAM_RESULT = ClientApi.EXAM + '/result';
  public static EXAM_RESULT_EDIT = ClientApi.EXAM_RESULT + '/edit';
  public static EXAM_ROUTINE = ClientApi.EXAM + '/routine';
  public static EXAM_ROUTINE_EDIT = ClientApi.EXAM_ROUTINE + '/edit';
  public static EXAM_SESSION = ClientApi.EXAM + '/session/list';
  public static GRADE = ClientApi.EXAM + '/grade';
  public static GRADE_ID = ClientApi.GRADE + '/{grade_id}';
  public static EXAM_TERM = ClientApi.EXAM + '/term';
  public static EXAM_TERM_LIST = ClientApi.EXAM_TERM + '/list';

  public static TRANSPORT = ClientApi.BASE + '/transport';
  public static TRANSPORT_LIST = ClientApi.TRANSPORT + '/list';
  public static TRANSPORT_ID = ClientApi.TRANSPORT + '/{transport_id}';
  public static TRANSPORT_EDIT = ClientApi.TRANSPORT + '/edit';
  public static TRANSPORT_ROUTE = ClientApi.TRANSPORT + '/route';
  public static TRANSPORT_ROUTE_ID = ClientApi.TRANSPORT_ROUTE + '/{route_id}';
  public static TRANSPORT_ROUTE_EDIT = ClientApi.TRANSPORT_ROUTE + '/edit';
  public static TRANSPORT_ROUTE_LIST = ClientApi.TRANSPORT_ROUTE + '/list';

  public static USER = ClientApi.BASE + '/user';
  public static USER_INFO = ClientApi.USER + '/{user_id}';

  public static ATTENDANCE = ClientApi.BASE + '/attendance';

  public static FEES = ClientApi.BASE + '/fee';
  public static FEES_DETAIL = ClientApi.FEES + '/{fee_id}';

  public static INVOICE = ClientApi.BASE + '/invoice';
  public static INVOICE_DETIAL = ClientApi.INVOICE + '/{invoice_id}';

  public static FILE = ClientApi.BASE + '/file';

  public static PDF=ClientApi.STAFF+'/{staff_id}/pdf';
  public static STUDENT_PDF =ClientApi.STUDENT + '/{student_id}/pdf';
}
