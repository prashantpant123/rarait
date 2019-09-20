package com.rarait.education.academic;

import com.rarait.education.academic.impl.AcademicSession;
import com.rarait.framework.repository.BaseJpaRepository;
import com.rarait.framework.shared.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface AcademicSessionRepository extends BaseJpaRepository<AcademicSession, Integer> {

    @Query("SELECT a FROM AcademicSession a WHERE a.institute.id= :instituteId AND a.status= :status")
    Page<AcademicSession> findAllByInstitute(@Param("instituteId") Integer id,
                                             @Param("status") Status status,
                                             Pageable page);

    @Query("SELECT a FROM AcademicSession a WHERE a.institute.id= :instituteId AND a.status= :status")
    List<AcademicSession> findAllByInstituteAndStatus(@Param("instituteId") Integer id,
                                             @Param("status") Status status,
                                             Pageable pageable);

    @Query("SELECT a FROM AcademicSession a WHERE a.institute.id= :instituteId AND a.id= :id")
    AcademicSession findByInstituteAndId(@Param("instituteId") Integer id,
                                         @Param("id") int asId);
}
