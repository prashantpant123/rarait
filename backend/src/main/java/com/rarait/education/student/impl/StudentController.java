package com.rarait.education.student.impl;

import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.route.InstituteRoute;
import com.rarait.education.student.StudentService;
import com.rarait.education.student.resource.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(InstituteRoute.STUDENT)
    public StudentCreateResponse addStudent(@RequestBody StudentRegisterRequest request) {
        return studentService.addStudent(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(InstituteRoute.STUDENT_EDIT)
    public void updateStudentDetail(@RequestBody StudentUpdateResource request) {
        studentService.updateStudent(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.STUDENT)
    public PagedResponse<StudentResponse> getAllStudents(@RequestParam(value = "page") int pageNumber,
                                                         @RequestParam(value = "class_id", required = false) Integer levelId,
                                                         @RequestParam(value = "sort_field", required = false) String sortField,
                                                         @RequestParam(value = "ascend", required = false) Boolean ascend) {
        return studentService.findAllStudentWithFilter(pageNumber, levelId, sortField, ascend);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.STUDENT_SEARCH)
    public PagedResponse<StudentResponse> searchStudentByFilter(@RequestParam(value = "page") int pageNumber,
                                                                @RequestParam(value = "class_id", required = false) Integer levelId,
                                                                @RequestParam(value = "search_param") String searchParam,
                                                                @RequestParam(value = "search_value") String searchValue) {
        return studentService.searchByFilter(pageNumber, levelId, searchParam, searchValue);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.STUDENT_ID)
    public StudentDetailResource getStudentDetail(@PathVariable("student_id") Long studentId) {
        return studentService.findDetailById(studentId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.STUDENT_REGISTRATION_CHECK)
    public RegistrationCheckResponse checkStudentRegistrationNumber(@PathVariable("registration_number") String registrationId) {
        return RegistrationCheckResponse.builder()
                .exist(studentService.checkRegistrationNumber(registrationId))
                .registrationNumber(registrationId)
                .build();
    }
}
