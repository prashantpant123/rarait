package com.rarait.education.student;

import com.rarait.education.document.DocumentType;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.student.impl.Student;
import com.rarait.education.student.resource.*;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since Sep 28, 2018
 */
public interface StudentService {

    StudentCreateResponse addStudent(StudentRegisterRequest request);

    void updateStudent(StudentUpdateResource request);

    PagedResponse<StudentResponse> findAllStudentWithFilter(int pageNumber, Integer levelId,
                                                            String sortField, Boolean ascend);

    PagedResponse<StudentResponse> searchByFilter(int pageNumber, Integer levelId, String searchParameter, String searchValue);

    Page<Student> findAllWithLevelAndInstitute(int pageNumber, Integer levelId, int instituteId);

    StudentDetailResource findDetailById(Long studentId);

    Student findStudent(Long studentId);

    Student findByIdAndInstitute(long studentId, int instituteId);

    void uploadDocument(MultipartFile file, DocumentType type, Long studentId);

    boolean checkRegistrationNumber(String registrationNumber);
}
