package com.rarait.education.structure.level.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.structure.level.LevelRoutineRepository;
import com.rarait.education.structure.level.LevelRoutineService;
import com.rarait.education.structure.level.LevelService;
import com.rarait.education.structure.level.resource.LevelRoutineCreateRequest;
import com.rarait.education.structure.subject.SubjectService;
import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class LevelRoutineServiceImpl implements LevelRoutineService {

    private final LevelRoutineRepository levelRoutineRepository;
    private final SubjectService subjectService;
    private final InstituteLoginSession instituteLoginSession;

    public LevelRoutineServiceImpl(LevelRoutineRepository levelRoutineRepository,
                                   SubjectService subjectService,
                                   InstituteLoginSession instituteLoginSession) {
        this.levelRoutineRepository = levelRoutineRepository;
        this.subjectService = subjectService;
        this.instituteLoginSession = instituteLoginSession;
    }

    @Override
    @Transactional
    public void createRoutine(List<LevelRoutineCreateRequest> requests) {
        Institute institute = instituteLoginSession.getCurrentInstitute();

        for (LevelRoutineCreateRequest request : requests) {
            LevelRoutine routine = new LevelRoutine();
            routine.setPeriodStartTime(Time.valueOf(request.getStartTime().toString()));
            routine.setPeriodEndTime(Time.valueOf(request.getEndTime().toString()));
            routine.setSubject(subjectService.findOneByIdAndInstitute(institute.getId(), request.getSubjectId()));
            routine.setDateOfWeek(request.getDayOfWeek());
            routine.setStatus(Status.ACTIVE);
            levelRoutineRepository.save(routine);
        }
    }
}
