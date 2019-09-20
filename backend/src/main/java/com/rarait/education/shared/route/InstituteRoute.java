package com.rarait.education.shared.route;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class  InstituteRoute extends BaseRoute {

    public static final String BASIC_INFO = EDUCATION + "/info";
    public static final String SUMMARY = EDUCATION + "/summary";

    public static final String TRANSPORT = EDUCATION + "/transport";
    public static final String TRANSPORT_LIST = TRANSPORT + "/list";
    public static final String TRANSPORT_ID = TRANSPORT + "/{transport_id}";
    public static final String TRANSPORT_ROUTE = TRANSPORT + "/route";
    public static final String TRANSPORT_ROUTE_ID = TRANSPORT_ROUTE + "/{transport_route_id}";
    public static final String TRANSPORT_ROUTE_LIST = TRANSPORT_ROUTE + "/list";

    public static final String FEE = EDUCATION + "/fee";
    public static final String FEE_UPDATE = FEE + "/edit";
    public static final String FEE_ID = FEE + "/{fee_id}";

    public static final String BILL = EDUCATION + "/bill-invoice";
    public static final String BILL_UPDATE = BILL + "/edit";
    public static final String BILL_NARRATION = BILL + "/narration";
    public static final String BILL_NARRATION_UPDATE = BILL_NARRATION + "/edit";

    public static final String STUDENT = EDUCATION + "/student";
    public static final String STUDENT_SEARCH = STUDENT + "/search";
    public static final String STUDENT_LEVEL = STUDENT + "/{level_id}";
    public static final String STUDENT_EDIT = STUDENT + "/edit";
    public static final String STUDENT_ID = STUDENT + "/{student_id}";
    public static final String STUDENT_REGISTRATION_CHECK = STUDENT + "/registration/{registration_number}/check";

    public static final String SUBJECT = EDUCATION + "/subject";
    public static final String SUBJECT_ID = SUBJECT + "/{subject_id}";
    public static final String SUBJECT_UPDATE = SUBJECT + "/edit";

    public static final String LEVEL = EDUCATION + "/level";
    public static final String LEVEL_ID = LEVEL + "/{level_id}";
    public static final String LEVEL_EDIT = LEVEL + "/edit";
    public static final String LEVEL_LIST = LEVEL + "/list";
    public static final String DEPARTMENT = EDUCATION + "/department";
    public static final String DEPARTMENT_LIST = DEPARTMENT + "/list";
    public static final String LEVEL_ROUTINE = LEVEL + "/routine";

    public static final String ACADEMIC_SESSION = EDUCATION + "/academic-session";
    public static final String ACADEMIC_SESSION_LIST = ACADEMIC_SESSION + "/list";
    public static final String ACADEMIC_SESSION_ID = ACADEMIC_SESSION + "/{academic_session_id}";
    public static final String ACADEMIC_SESSION_EDIT = ACADEMIC_SESSION + "/edit";

    public static final String EXAM = EDUCATION + "/exam";
    public static final String EXAM_ID = EXAM + "/{exam_id}";
    public static final String EXAM_EDIT = EXAM + "/edit";
    public static final String EXAM_LIST = EXAM + "/list";
    public static final String EXAM_SESSION_LIST = EXAM + "/session/list";
    public static final String EXAM_TERM = EXAM + "/term";
    public static final String EXAM_TERM_LIST = EXAM_TERM + "/list";

    public static final String EXAM_ROUTINE = EXAM + "/routine";
    public static final String EXAM_ROUTINE_ID = EXAM_ROUTINE + "/{routine_id}";
    public static final String EXAM_REMARK = EXAM + "/remarks";

    public static final String GRADE = EXAM + "/grade";
    public static final String GRADE_ID = GRADE + "/{grade_id}";

    public static final String EMPLOYEE = EDUCATION + "/employee";
    public static final String EMPLOYEE_ID = EMPLOYEE + "/{employee_id}";
    public static final String EMPLOYEE_EDIT = EMPLOYEE + "/edit";
    public static final String EMPLOYEE_TYPE = EMPLOYEE + "/type";

    public static final String ATTENDANCE = EDUCATION + "/attendance";
    public static final String ATTENDANCE_TYPE = ATTENDANCE + "/type";

    public static final String SECTION = LEVEL + "/section";
    public static final String SECTION_ID = SECTION + "/{section_id}";

    public static final String SECTION_LIST = LEVEL + "/{class_id}/section/list";

    public static final String FILE_UPLOAD = EDUCATION + "/file/upload";
    public static final String FILE_DOWNLOAD = EDUCATION + "/file/download/{fileName:.+}";
    public static final String FILE_LIST = EDUCATION + "/file/list";

    public static final String INSTITUTE = EDUCATION;
    public static final String INSTITUTE_FILE = EDUCATION + "/file";
    public static final String INSTITUTE_FILE_ID = INSTITUTE + "/{institute_id}/file";
    public static final String INSTITUTE_STAFF_FILE_ID = INSTITUTE_FILE_ID + "/{user_id}";

    public static final String PDF=EMPLOYEE+"/{employee_id}/pdf";
    public static final String STUDENT_PDF= STUDENT+ "/{student_id}/pdf";

    private InstituteRoute() {
    }
}
