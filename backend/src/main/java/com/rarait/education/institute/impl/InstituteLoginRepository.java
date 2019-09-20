package com.rarait.education.institute.impl;

import com.rarait.framework.repository.BaseJpaRepository;
import com.rarait.framework.shared.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface InstituteLoginRepository extends BaseJpaRepository<InstituteLogin, Integer> {

    InstituteLogin findOneByInstituteAndId(int instituteId, int instituteLoginId);

    @Query("SELECT i.institute FROM InstituteLogin i WHERE i.user.id=?1 AND i.status=?2")
    Institute findByLoginId(long loginId, Status status);

    @Query("SELECT i FROM InstituteLogin i WHERE i.institute.id= :institute")
    List<InstituteLogin> findAllByInstitute(@Param("institute") int instituteId);
}