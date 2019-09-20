package com.rarait.education.exam;

import com.rarait.education.exam.resource.GradeCreateRequest;
import com.rarait.education.exam.resource.GradeDetailResource;
import com.rarait.education.shared.PagedResponse;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface GradeService {

    void createGrade(GradeCreateRequest request);

    GradeDetailResource findDetailForId(int gradeId);

    PagedResponse<GradeDetailResource> findAllGrade(int pageNumber);
}
