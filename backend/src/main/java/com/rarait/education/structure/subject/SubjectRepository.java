package com.rarait.education.structure.subject;

import com.rarait.education.structure.subject.impl.Subject;
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
public interface SubjectRepository extends BaseJpaRepository<Subject, Integer> {

    @Query("SELECT s FROM Subject s WHERE s.id=?1 AND s.institute.id=?2")
    Subject findByIdAndInstitute(int subjectId, int institute);

//    @Query("SELECT s FROM Subject s WHERE s.institute.id= :institute")
//    Page<Subject> findAllSubject(@Param("institute") int instituteId, Pageable pageable);
//
//    @Query("SELECT s FROM Subject s WHERE s.institute.id= :institute AND s.level.id= :levelId")
//    Page<Subject> findAllByInstituteAndLevel(@Param("institute") int instituteId,
//                                             @Param("levelId") int levelId,
//                                             Pageable pageable);

    @Query("SELECT s FROM Subject s WHERE s.institute.id= :institute AND s.level.id= :levelId ORDER BY s.name")
    List<Subject> findAllByClassAndInstitute(@Param("institute") int instituteId,
                                             @Param("levelId") int levelId);
}