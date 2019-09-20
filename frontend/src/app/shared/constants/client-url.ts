export class ClientUrl {
  public static BASE = '/client';

  public static DASHBOARD = ClientUrl.BASE + '/dashboard';

  public static STUDENT = ClientUrl.BASE + '/student';

  public static ACADEMIC_SESSION = ClientUrl.BASE + '/academic-session';
  public static ACADEMIC_SESSION_CREATE = ClientUrl.ACADEMIC_SESSION + '/create';

  public static CLASS = ClientUrl.BASE + '/class';
  public static CLASS_CREATE = ClientUrl.CLASS + '/create';
  public static SECTION = ClientUrl.CLASS + '/section';
  public static SECTION_CREATE = ClientUrl.SECTION + '/create';

  public static EXAM = ClientUrl.BASE + '/exam';
  public static EXAM_RESULT = ClientUrl.EXAM + '/result';
  public static EXAM_ROUTINE = ClientUrl.EXAM + '/routine';
  public static GRADE = ClientUrl.EXAM + '/grade';
  public static TERM = ClientUrl.EXAM + '/term';

  public static TRANSPORT = ClientUrl.BASE + '/transport';
  public static TRANSPORT_ROUTE = ClientUrl.TRANSPORT + '/route';

  public static SUBJECT = ClientUrl.BASE + '/subject';

  public static STAFF = ClientUrl.BASE + '/staff';

  public static ATTENDANCE = ClientUrl.BASE + '/attendance';

  public static BILLING = ClientUrl.BASE + '/billing';
  public static FEE = ClientUrl.BILLING + '/fee';
  public static FEE_CREATE = ClientUrl.FEE + '/create';

  public static INVOICE = ClientUrl.BILLING + '/invoice';
  public static INVOICE_CREATE = ClientUrl.INVOICE + '/create';
  public static INVOICE_DETAIL = ClientUrl.INVOICE + '/detail';

}
