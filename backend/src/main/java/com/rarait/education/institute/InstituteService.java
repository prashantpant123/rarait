package com.rarait.education.institute;

import com.rarait.education.document.DocumentType;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.institute.resource.*;
import com.rarait.education.shared.PagedResponse;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface InstituteService {

    InstituteCreateResponse addInstitute(InstituteCreateRequest request);

    void updateInstitute(InstituteUpdateResource request);

    void updateInstituteStatus(InstituteStatusResource request);

    PagedResponse<InstituteResponse> findAllActive(int pageNumber);

    InstituteDetailResource findInstituteDetail(int schoolId);

    void updateInstituteDetail(int instituteId, InstituteCreateRequest request);

    Institute findOneById(int instituteId);

    Institute findByLoginId(long loginId);

    InstituteBasicInfo findBasicInfo();

    void uploadDocumentForAdmin(MultipartFile file, DocumentType type, Integer instituteId);

    void uploadDocumentForInstitute(MultipartFile file, DocumentType type, Long userId);

    Resource getDocument(String fileName, Long userId, int instituteId);

    Resource getDocument(String fileName, int instituteId);

    boolean checkInstituteRegistrationPrefix(String registrationPrefix);
}
