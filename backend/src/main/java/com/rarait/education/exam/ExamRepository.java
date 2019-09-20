package com.rarait.education.exam;

import com.rarait.education.exam.impl.Exam;
import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface ExamRepository extends BaseJpaRepository<Exam, Integer> {

    Exam findById(int examId);

    @Query("SELECT e FROM Exam e WHERE e.academicSession.institute.id= :institute AND e.id= :examId")
    Exam findByInstituteAndId(@Param("institute") int instituteId,
                              @Param("examId") int examId);

    @Query("SELECT e FROM Exam e WHERE e.academicSession.institute.id= :institute")
    Page<Exam> findAllByInstitute(@Param("institute") int instituteId, Pageable pageable);

    @Query("SELECT e FROM Exam e WHERE e.academicSession.institute.id= :institute ORDER BY e.id DESC")
    List<Exam> findAllListByInstitute(@Param("institute") int instituteId);
}