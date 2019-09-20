package com.rarait.education.structure.subject.impl;

import com.rarait.education.academic.impl.AcademicSession;
import com.rarait.education.structure.resource.SubjectCreateRequest;
import com.rarait.education.structure.resource.SubjectResource;
import com.rarait.education.structure.resource.SubjectUpdateRequest;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.structure.level.LevelService;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.shared.PagedRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.structure.subject.SubjectRepository;
import com.rarait.education.structure.subject.SubjectService;
import com.rarait.education.structure.subject.SubjectValidator;
import com.rarait.framework.shared.InputUtil;
import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectValidator subjectValidator;
    private final InstituteLoginSession instituteLoginSession;
    private final LevelService levelService;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository,
                              SubjectValidator subjectValidator,
                              InstituteLoginSession instituteLoginSession,
                              LevelService levelService) {
        this.subjectRepository = subjectRepository;
        this.subjectValidator = subjectValidator;
        this.instituteLoginSession = instituteLoginSession;
        this.levelService = levelService;
    }

    @Override
    @Transactional
    public void addSubject(SubjectCreateRequest request) {
        subjectValidator.validate(request);
        Institute institute = instituteLoginSession.getCurrentInstitute();

        Subject subject = new Subject();
        subject.setCode(request.getCode());
        subject.setName(request.getName());
        subject.setDescription(request.getDescription());
        subject.setStatus(Status.INACTIVE);
        subject.setTheoryFullMark(request.getFullMark());
        subject.setTheoryPassMark(request.getPassMark());
        subject.setPracticalFullMark(request.getPracticalFullMark());
        subject.setPracticalPassMark(request.getPracticalPassMark());
        subject.setLevel(levelService.getLevelById(request.getLevelId()));
        subject.setOptional(request.isOptional());
        subject.setPractical(request.isPractical());
        subject.setInstitute(institute);
        subjectRepository.save(subject);
    }

    @Override
    public void updateSubject(SubjectUpdateRequest request) {
        subjectValidator.validate(request);

        Institute institute = instituteLoginSession.getCurrentInstitute();
        Subject subject = subjectRepository.findByIdAndInstitute(request.getId(), institute.getId());
        subject.setName(request.getName());
        subject.setCode(request.getCode());
        subject.setTheoryFullMark(request.getFullMark());
        subject.setTheoryPassMark(request.getPassMark());
        subject.setPracticalFullMark(request.getPracticalFullMark());
        subject.setPracticalPassMark(request.getPracticalPassMark());
        subjectRepository.save(subject);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Subject getSubjectById(int subjectId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        return subjectRepository.findByIdAndInstitute(subjectId, institute.getId());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Subject findOneByIdAndInstitute(int subjectId, int instituteId) {
        return subjectRepository.findByIdAndInstitute(subjectId, instituteId);
    }

    @Override
    @Transactional
    public PagedResponse<SubjectResource> findAllSubjects(int pageNumber, Integer levelId,
                                                          String sortField, Boolean ascend) {
        Institute institute = instituteLoginSession.getCurrentInstitute();

        Page<Subject> subjects = subjectRepository.findAll((Root<Subject> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                    Path<Long> path = root.get("institute").get("id");
                    Predicate p = cb.equal(path, institute.getId());
                    if (levelId != null && levelId > 0) {
                        p = cb.and(p, cb.equal(root.get("level").get("id"), levelId));
                    }
                    return p;
                }, InputUtil.isEmpty(sortField) || InputUtil.isEmpty(ascend) ? PagedRequest.get(pageNumber, Sort.by("level", "name")) :
                        PagedRequest.get(pageNumber, ascend ? Sort.by("name").ascending() : Sort.by("name").descending())
        );
        return new PagedResponse<>(subjects.getTotalElements(),
                subjects.getTotalPages(),
                pageNumber,
                SubjectConvert.convertList(subjects.getContent()));
    }

    @Override
    @Transactional
    public SubjectResource findSubjectById(int subjectId) {
        return SubjectConvert.convert(getSubjectById(subjectId));
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Subject> findAllSubjectForClass(int classId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        return subjectRepository.findAllByClassAndInstitute(institute.getId(), classId);
    }
}