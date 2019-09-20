package com.rarait.education.summary;

import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface SummaryRepository extends BaseJpaRepository<Summary, Short> {

    @Query("SELECT s FROM Summary s WHERE s.institute.id= :instituteId")
    Summary findOneByInstitute(@Param("instituteId")int instituteId);
}
