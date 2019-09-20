package com.rarait.education.exam;

import com.rarait.education.exam.impl.Grade;
import com.rarait.framework.repository.BaseJpaRepository;
import com.rarait.framework.shared.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface GradeRepository extends BaseJpaRepository<Grade, Integer> {

    @Query("SELECT g FROM Grade g WHERE g.institute.id= :instituteId AND g.status= :status")
    Page<Grade> findAllByInstituteAndStatus(@Param("instituteId") int instituteId,
                                            @Param("status") Status status,
                                            Pageable pageable);

    @Query("SELECT g FROM Grade g WHERE g.institute.id= :instituteId AND g.id= :id")
    Optional<Grade> findOneByIdAndInstitute(@Param("instituteId") int instituteId,
                                           @Param("id") int id);
}
