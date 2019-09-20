package com.rarait.education.academic;

import com.rarait.education.academic.impl.AcademicSession;
import com.rarait.education.academic.resource.AcademicSessionCreateRequest;
import com.rarait.education.academic.resource.AcademicSessionDropdownResource;
import com.rarait.education.academic.resource.AcademicSessionResource;
import com.rarait.education.shared.PagedResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface AcademicSessionService {

    void createAcademicSession(AcademicSessionCreateRequest request);

    PagedResponse<AcademicSessionResource> getAllAcademicSession(int pageNumber, String sortField,
                                                                 Boolean ascend, Boolean... session);

    List<AcademicSessionDropdownResource> getAllAcademicSessionDropdownResources();

    AcademicSession findOneByInstituteAndId(int instituteId, int academicSessionId);

    AcademicSessionResource findDetailForId(int academicSessionId);
}
