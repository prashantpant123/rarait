package com.rarait.education.exam.impl;

import com.rarait.education.academic.AcademicSessionService;
import com.rarait.education.exam.*;
import com.rarait.education.exam.resource.*;
import com.rarait.education.exam.util.ExamConvert;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.shared.PagedRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.framework.date.CalendarConversion;
import com.rarait.framework.exception.ClientRestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ExamValidator examValidator;
    private final InstituteLoginSession instituteLoginSession;
    private final AcademicSessionService academicSessionService;
    private final ExamTermService examTermService;

    @Autowired
    public ExamServiceImpl(ExamRepository examRepository,
                           ExamValidator examValidator,
                           InstituteLoginSession instituteLoginSession,
                           AcademicSessionService academicSessionService,
                           ExamTermService examTermService) {
        this.examRepository = examRepository;
        this.examValidator = examValidator;
        this.instituteLoginSession = instituteLoginSession;
        this.academicSessionService = academicSessionService;
        this.examTermService = examTermService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addExam(ExamCreateRequest request) {
        examValidator.validate(request);
        Institute institute = instituteLoginSession.getCurrentInstitute();

        Exam exam = new Exam();
        exam.setStartDate(request.getStartDate());
        exam.setEndDate(request.getEndDate());
        exam.setStartDateBs(CalendarConversion.adToBs(request.getStartDate()));
        exam.setEndDateBs(CalendarConversion.adToBs(request.getEndDate()));
        exam.setWeightage(request.getWeightage());
        exam.setCategory(examTermService.findOneById(institute.getId(), request.getTermId()));
        exam.setAcademicSession(academicSessionService.findOneByInstituteAndId(institute.getId(), request.getAcademicSessionId()));
        examRepository.save(exam);
    }

    @Override
    public void updateExam(ExamUpdateRequest request) {
        examValidator.validate(request);
        Institute institute = instituteLoginSession.getCurrentInstitute();

        Exam exam = examRepository.findByInstituteAndId(
                instituteLoginSession.getCurrentInstitute().getId(),
                request.getId());
        exam.setCategory(examTermService.findOneById(
                institute.getId(),
                request.getTermId()));
        exam.setStartDate(request.getStartDate());
        exam.setEndDate(request.getEndDate());
        exam.setStartDateBs(CalendarConversion.adToBs(request.getStartDate()));
        exam.setEndDateBs(CalendarConversion.adToBs(request.getEndDate()));
        exam.setWeightage(request.getWeightage());
        examRepository.save(exam);
    }


    @Override
    @Transactional
    public Exam getExamForCurrentLogin(int examId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Optional<Exam> exams = Optional.of(examRepository.findByInstituteAndId(institute.getId(), examId));
        return exams.orElseThrow(() -> new ClientRestException("Request failed: exam not found"));
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Exam findOneByIdAndInstitute(int examId, int instituteId) {
        Optional<Exam> exams = Optional.of(examRepository.findByInstituteAndId(instituteId, examId));
        return exams.orElseThrow(() -> new ClientRestException("Request failed: exam not found"));
    }

    @Override
    @Transactional
    public ExamDetailResource getExamDetailId(int examId) {
        return ExamConvert.convertDetail(getExamForCurrentLogin(examId));
    }

    @Override
    @Transactional
    public PagedResponse<ExamResource> findAllExam(int pageNumber) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Page<Exam> exams = examRepository.findAllByInstitute(
                institute.getId(),
                PagedRequest.get(pageNumber, Sort.by("id").descending()));

        return new PagedResponse<>(exams.getTotalElements(),
                exams.getTotalPages(),
                exams.getNumber(),
                ExamConvert.convertToList(exams.getContent()));
    }

    @Override
    @Transactional
    public List<ExamDropdownResource> findAllExamList() {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        return ExamConvert.convertDropdownList(examRepository.findAllListByInstitute(institute.getId()));
    }

    @Override
    @Transactional
    public List<DropdownListResource> findExamSessionList() {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        return ExamConvert.convertExamSessionList(examRepository.findAllListByInstitute(institute.getId()));
    }
}