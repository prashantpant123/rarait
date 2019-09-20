package com.rarait.education.exam;

import com.rarait.education.exam.impl.ExamTerm;
import com.rarait.education.exam.resource.ExamCategoryCreateRequest;
import com.rarait.education.exam.resource.ExamCategoryResource;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.resource.DropdownListResource;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface ExamTermService {

    void createTerm(ExamCategoryCreateRequest request);

    ExamTerm findOneById(int instituteId, int categoryId);

    PagedResponse<ExamCategoryResource> findAllActiveByInstitute(int pageNumber);

    List<DropdownListResource> findExamTermList();
}
