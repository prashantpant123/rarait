package com.rarait.education.account.fee;

import com.rarait.education.account.fee.impl.FeeStructure;
import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface FeeStructureRepository extends BaseJpaRepository<FeeStructure, Integer> {

    @Query("SELECT f FROM FeeStructure f WHERE f.id=?1 AND f.institute.id=?2")
    Optional<FeeStructure> findByIdAndInstitute(int feeStructureId, int instituteId);

    @Query("SELECT f FROM FeeStructure f WHERE f.institute.id= :instituteId")
    Page<FeeStructure> findAllByInstitute(@Param("instituteId") int instituteId,
                                          Pageable pageable);
}