package com.rarait.education.structure.subject;

import com.rarait.education.structure.resource.SubjectCreateRequest;
import com.rarait.education.structure.resource.SubjectResource;
import com.rarait.education.structure.resource.SubjectUpdateRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.structure.subject.impl.Subject;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface SubjectService {

    void addSubject(SubjectCreateRequest request);

    void updateSubject(SubjectUpdateRequest request);

    Subject getSubjectById(int subjectId);

    Subject findOneByIdAndInstitute(int subjectId, int instituteId);

    PagedResponse<SubjectResource> findAllSubjects(int page, Integer levelId,
                                                   String sortField, Boolean ascend);

    SubjectResource findSubjectById(int subjectId);

    List<Subject> findAllSubjectForClass(int classId);
}
