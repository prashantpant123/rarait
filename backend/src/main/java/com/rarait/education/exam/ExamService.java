package com.rarait.education.exam;

import com.rarait.education.exam.impl.Exam;
import com.rarait.education.exam.impl.ExamRoutine;
import com.rarait.education.exam.resource.*;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.resource.DropdownListResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface ExamService {

    void addExam(ExamCreateRequest request);

    void updateExam(ExamUpdateRequest request);

    Exam getExamForCurrentLogin(int examId);

    Exam findOneByIdAndInstitute(int examId, int instituteId);

    ExamDetailResource getExamDetailId(int examId);

    PagedResponse<ExamResource> findAllExam(int pageNumber);

    List<ExamDropdownResource> findAllExamList();

    List<DropdownListResource> findExamSessionList();
}