package com.rarait.education.exam.impl;

import com.rarait.education.exam.util.ExamRoutineConvert;
import com.rarait.education.structure.subject.impl.Subject;
import com.rarait.education.structure.subject.SubjectService;
import com.rarait.education.exam.ExamRoutineRepository;
import com.rarait.education.exam.ExamRoutineService;
import com.rarait.education.exam.ExamService;
import com.rarait.education.exam.resource.ExamRoutineCreateList;
import com.rarait.education.exam.resource.ExamRoutineCreateRequest;
import com.rarait.education.exam.resource.ExamRoutineResource;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.structure.level.LevelService;
import com.rarait.education.structure.level.impl.Level;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.shared.PagedRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.framework.exception.ClientRestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class ExamRoutineServiceImpl implements ExamRoutineService {

    private final ExamRoutineRepository examRoutineRepository;
    private final InstituteLoginSession instituteLoginSession;
    private final ExamService examService;
    private final SubjectService subjectService;
    private final LevelService levelService;

    @Autowired
    public ExamRoutineServiceImpl(ExamRoutineRepository examRoutineRepository,
                                  InstituteLoginSession instituteLoginSession,
                                  ExamService examService,
                                  SubjectService subjectService,
                                  LevelService levelService) {
        this.examRoutineRepository = examRoutineRepository;
        this.instituteLoginSession = instituteLoginSession;
        this.examService = examService;
        this.subjectService = subjectService;
        this.levelService = levelService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addExamRoutines(ExamRoutineCreateList request) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Exam exam = examService.findOneByIdAndInstitute(request.getExamId(), institute.getId());
        Level level = levelService.findOneByIdAndInstitute(request.getLevelId(), institute.getId());
        request.getData().stream()
                .forEach(req -> {
                    if (req.getId() > 0) {
                        updateExamRoutine(req, institute.getId());
                    } else {
                        createExamRoutine(req, exam, level, institute.getId());
                    }
                });
    }

    private void createExamRoutine(ExamRoutineCreateRequest request, Exam exam, Level level, int instituteId) {
        ExamRoutine examRoutine = new ExamRoutine();
        examRoutine.setStartTime(Time.valueOf(request.getStartTime()));
        examRoutine.setEndTime(Time.valueOf(request.getEndTime()));
        examRoutine.setExamDate(request.getExamDate());
        examRoutine.setRemarks(request.getRemarks());
        examRoutine.setExam(exam);
        examRoutine.setLevel(level);
        Subject subject = subjectService.findOneByIdAndInstitute(request.getSubjectId(), instituteId);
        examRoutine.setSubject(subject);
        examRoutine.setFullMark(request.getFullMark() == 0 ? subject.getTheoryFullMark() : request.getFullMark());
        examRoutine.setPassMark(request.getPassMark() == 0 ? subject.getTheoryPassMark(): request.getPassMark());
        examRoutineRepository.save(examRoutine);
    }

    public void updateExamRoutine(ExamRoutineCreateRequest request, int instituteId) {
        Optional<ExamRoutine> schedules = Optional.of(examRoutineRepository.findByInstituteAndId(
                instituteId, request.getId()));
        ExamRoutine schedule = schedules.orElseThrow(() ->
                new ClientRestException("Request failed: exam schedule not found"));
        schedule.setExamDate(request.getExamDate());
        schedule.setStartTime(Time.valueOf(request.getStartTime()));
        schedule.setEndTime(Time.valueOf(request.getEndTime()));
        schedule.setRemarks(request.getRemarks());
        examRoutineRepository.save(schedule);
    }

    @Override
    @Transactional
    public ExamRoutine getExamRoutineForCurrentLogin(int examRoutineId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Optional<ExamRoutine> examSchedules = Optional.of(examRoutineRepository.findByInstituteAndId(
                institute.getId(), examRoutineId));
        return examSchedules.orElseThrow(() -> new ClientRestException("Request failed: exam schedule not found"));
    }

    @Override
    @Transactional
    public PagedResponse<ExamRoutineResource> findAllExamRoutine(int examId, int classId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Page<ExamRoutine> exams = examRoutineRepository.findByInstituteAndExam(
                institute.getId(), examId, classId,
                PagedRequest.getLarge(1, Sort.by("id").descending()));
        if (exams == null || exams.isEmpty()) {
            List<Subject> subjects = subjectService.findAllSubjectForClass(classId);
            return new PagedResponse<>(subjects.size(),
                    1,
                    1,
                    ExamRoutineConvert.convertSubjectList(subjects));
        }
        return new PagedResponse<>(exams.getTotalElements(),
                exams.getTotalPages(),
                1,
                ExamRoutineConvert.convertScheduleToList(exams.getContent()));
    }
}
