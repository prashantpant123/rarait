package com.rarait.education.exam.impl;

import com.rarait.education.exam.GradeRepository;
import com.rarait.education.exam.GradeService;
import com.rarait.education.exam.GradeValidator;
import com.rarait.education.exam.resource.GradeCreateRequest;
import com.rarait.education.exam.resource.GradeDetailResource;
import com.rarait.education.exam.util.GradeConvert;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.shared.BaseService;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.shared.PagedRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Service
@Slf4j
public class GradeServiceImpl extends BaseService implements GradeService {

    private final GradeRepository gradeRepository;
    private final GradeValidator gradeValidator;

    public GradeServiceImpl(GradeRepository gradeRepository,
                            GradeValidator gradeValidator,
                            InstituteLoginSession instituteLoginSession) {
        super(instituteLoginSession);
        this.gradeRepository = gradeRepository;
        this.gradeValidator = gradeValidator;
    }

    @Override
    public void createGrade(GradeCreateRequest request) {
        gradeValidator.validate(request);
        Institute institute = getInstitute();

        Grade grade = new Grade();
        grade.setHighMark(request.getHighMark());
        grade.setLowMark(request.getLowMark());
        grade.setValue(request.getValue());
        grade.setStatus(Status.ACTIVE);
        grade.setInstitute(institute);
        gradeRepository.save(grade);
    }

    @Override
    public GradeDetailResource findDetailForId(int gradeId) {
        Grade grade = findOne(gradeId);
        return GradeConvert.convertDetail(grade);
    }

    @Override
    public PagedResponse<GradeDetailResource> findAllGrade(int pageNumber) {
        Institute institute = getInstitute();
        Page<Grade> grades = gradeRepository.findAllByInstituteAndStatus(institute.getId(), Status.ACTIVE, PagedRequest.get(pageNumber, Sort.by("value")));
        return new PagedResponse(grades.getTotalElements(), grades.getTotalPages(), pageNumber,
                GradeConvert.convertDetailList(grades.getContent()));
    }

    private Grade findOne(int gradeId) {
        Institute institute = getInstitute();
        Optional<Grade> grade = gradeRepository.findOneByIdAndInstitute(institute.getId(), gradeId);
        return grade.orElseThrow(() -> new ClientRestException("Grade not found"));
    }
}
