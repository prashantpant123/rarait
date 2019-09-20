package com.rarait.education.academic.impl;

import com.rarait.education.academic.AcademicSessionRepository;
import com.rarait.education.academic.AcademicSessionService;
import com.rarait.education.academic.AcademicSessionValidator;
import com.rarait.education.academic.resource.AcademicSessionCreateRequest;
import com.rarait.education.academic.resource.AcademicSessionDropdownResource;
import com.rarait.education.academic.resource.AcademicSessionResource;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.shared.PagedRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.framework.date.CalendarConversion;
import com.rarait.framework.shared.DateUtil;
import com.rarait.framework.shared.InputUtil;
import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class AcademicSessionServiceImpl implements AcademicSessionService {

    private final AcademicSessionRepository academicSessionRepository;
    private final InstituteLoginSession instituteLoginSession;
    private final AcademicSessionValidator academicSessionValidator;

    @Autowired
    public AcademicSessionServiceImpl(
            AcademicSessionRepository academicSessionRepository,
            InstituteLoginSession instituteLoginSession,
            AcademicSessionValidator academicSessionValidator
    ) {
        this.academicSessionRepository = academicSessionRepository;
        this.instituteLoginSession = instituteLoginSession;
        this.academicSessionValidator = academicSessionValidator;
    }

    @Override
    @Transactional
    public void createAcademicSession(final AcademicSessionCreateRequest request) {
        log.debug(request.toString());
        academicSessionValidator.validate(request);

        Institute institute = instituteLoginSession.getCurrentInstitute();
        AcademicSession session = new AcademicSession();
        session.setName(request.getName());
        session.setStartDateAd(request.getStartDateAD());
        session.setEndDateAd(request.getEndDateAD());
        session.setStartDateBs(CalendarConversion.adToBs(request.getStartDateAD()));
        session.setEndDateBs(CalendarConversion.adToBs(request.getEndDateAD()));
        session.setCurrentSession(request.isCurrentSession());
        session.setInstitute(institute);
        session.setStatus(Status.ACTIVE);
        academicSessionRepository.save(session);
    }

    //    @Override
    @Transactional
    public void updateAcademicSession() {
        //TODO get academic session id and query result for update
        Institute institute = instituteLoginSession.getCurrentInstitute();
    }

    @Override
    @Transactional
    public PagedResponse<AcademicSessionResource> getAllAcademicSession(int pageNumber, String sortField,
                                                                        Boolean ascend, Boolean... session) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Page<AcademicSession> academicSessions = academicSessionRepository.findAll((Root<AcademicSession> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                    Path<Long> path = root.get("institute").get("id");
                    Predicate p = cb.equal(path, institute.getId());
                    p = cb.and(p, cb.equal(root.get("status"), Status.ACTIVE));
                    if (!InputUtil.isEmpty(session)) {
                        p = cb.and(p, root.get("currentSession").in(session));
                    }
                    return p;
                }, InputUtil.isEmpty(sortField) || InputUtil.isEmpty(ascend) ? PagedRequest.get(pageNumber, Sort.by("id").descending()) :
                        PagedRequest.get(pageNumber, ascend ? Sort.by("name").ascending() : Sort.by("name").descending())
        );

        return new PagedResponse<>(academicSessions.getTotalElements(),
                academicSessions.getTotalPages(),
                pageNumber,
                AcademicSessionConvert.convertList(academicSessions.getContent()));
    }

    @Override
    @Transactional
    public List<AcademicSessionDropdownResource> getAllAcademicSessionDropdownResources() {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        return AcademicSessionConvert.convertDDList(academicSessionRepository.findAllByInstituteAndStatus(
                institute.getId(), Status.ACTIVE,
                PageRequest.of(0, 100, Sort.by("id").descending())));
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public AcademicSession findOneByInstituteAndId(int instituteId, int academicSessionId) {
        return academicSessionRepository.findByInstituteAndId(instituteId, academicSessionId);
    }

    @Override
    @Transactional
    public AcademicSessionResource findDetailForId(int academicSessionId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        return AcademicSessionConvert.convert(academicSessionRepository.findByInstituteAndId(institute.getId(), academicSessionId));
    }

}
