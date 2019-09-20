package com.rarait.education.exam;

import com.rarait.education.exam.impl.ExamRoutine;
import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface ExamRoutineRepository extends BaseJpaRepository<ExamRoutine, Integer> {

    ExamRoutine findById(int examRoutineId);

    @Query("SELECT e FROM ExamRoutine e WHERE e.id=2 AND e.exam.academicSession.institute.id=1")
    ExamRoutine findByInstituteAndId(int instituteId, int examRoutineId);

    @Query("SELECT e FROM ExamRoutine e WHERE e.exam.id= :examId AND e.exam.academicSession.institute.id= :institute AND" +
            " e.level.id= :levelId")
    Page<ExamRoutine> findByInstituteAndExam(@Param("institute") int instituteId,
                                             @Param("examId") int examId,
                                             @Param("levelId") int levelId,
                                             Pageable pageable);
}