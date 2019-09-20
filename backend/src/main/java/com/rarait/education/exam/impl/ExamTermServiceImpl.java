package com.rarait.education.exam.impl;

import com.rarait.education.exam.ExamTermRepository;
import com.rarait.education.exam.ExamTermService;
import com.rarait.education.exam.resource.ExamCategoryCreateRequest;
import com.rarait.education.exam.resource.ExamCategoryResource;
import com.rarait.education.exam.util.ExamConvert;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.shared.PagedRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class ExamTermServiceImpl implements ExamTermService {

    private final ExamTermRepository examTermRepository;
    private final InstituteLoginSession instituteLoginSession;

    public ExamTermServiceImpl(ExamTermRepository examTermRepository,
                               InstituteLoginSession instituteLoginSession) {
        this.examTermRepository = examTermRepository;
        this.instituteLoginSession = instituteLoginSession;
    }

    @Override
    @Transactional
    public void createTerm(ExamCategoryCreateRequest request) {
        ExamTerm category = new ExamTerm();
        category.setName(request.getName());
        category.setStatus(Status.ACTIVE);
        category.setWeightage(request.getWeightage());
        category.setInstitute(instituteLoginSession.getCurrentInstitute());
        examTermRepository.save(category);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public ExamTerm findOneById(int instituteId, int categoryId) {
        return examTermRepository.findOneByInstitute(instituteId, categoryId);
    }

    @Override
    @Transactional
    public PagedResponse<ExamCategoryResource> findAllActiveByInstitute(int pageNumber) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Page<ExamTerm> categories = examTermRepository.findAllByInstituteAndStatus(institute.getId(), Status.ACTIVE,
                PagedRequest.get(pageNumber, Sort.by("id").descending()));
        return new PagedResponse<>(categories.getTotalElements(), categories.getTotalPages(), pageNumber,
                ExamConvert.convertTermList(categories.getContent()));
    }

    @Override
    @Transactional
    public List<DropdownListResource> findExamTermList() {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        return ExamConvert.convertTermDropdownList(examTermRepository.findAllByInstituteAndStatus(institute.getId(), Status.ACTIVE));
    }
}
