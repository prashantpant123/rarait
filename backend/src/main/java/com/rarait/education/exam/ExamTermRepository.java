package com.rarait.education.exam;

import com.rarait.education.exam.impl.ExamTerm;
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
public interface ExamTermRepository extends BaseJpaRepository<ExamTerm, Integer> {

    @Query("SELECT e FROM ExamTerm e WHERE e.institute.id= :instituteId AND e.id= :id")
    ExamTerm findOneByInstitute(@Param("instituteId") int instituteId,
                                @Param("id") int id);

    @Query("SELECT e FROM ExamTerm e WHERE e.institute.id= :instituteId AND e.status= :status ORDER BY e.name")
    List<ExamTerm> findAllByInstituteAndStatus(@Param("instituteId") int instituteId,
                                               @Param("status") Status status);

    @Query("SELECT e FROM ExamTerm e WHERE e.institute.id= :instituteId AND e.status= :status ORDER BY e.name")
    Page<ExamTerm> findAllByInstituteAndStatus(@Param("instituteId") int instituteId,
                                               @Param("status") Status status,
                                               Pageable pageable);

}
