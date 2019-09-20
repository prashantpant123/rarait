package com.rarait.education.exam;

import com.rarait.education.exam.impl.ExamRoutine;
import com.rarait.education.exam.resource.ExamRoutineCreateList;
import com.rarait.education.exam.resource.ExamRoutineCreateRequest;
import com.rarait.education.exam.resource.ExamRoutineResource;
import com.rarait.education.exam.resource.ExamRoutineUpdateRequest;
import com.rarait.education.shared.PagedResponse;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface ExamRoutineService {

    void addExamRoutines(ExamRoutineCreateList request);

    ExamRoutine getExamRoutineForCurrentLogin(int examRoutineId);

    PagedResponse<ExamRoutineResource> findAllExamRoutine(int examId, int pageNumber);
}
