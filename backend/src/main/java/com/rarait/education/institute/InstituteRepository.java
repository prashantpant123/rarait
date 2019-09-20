package com.rarait.education.institute;

import com.rarait.education.institute.impl.Institute;
import com.rarait.framework.repository.BaseJpaRepository;
import com.rarait.framework.shared.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface InstituteRepository extends BaseJpaRepository<Institute, Integer> {

    @Query("SELECT i FROM Institute i WHERE i.id= :id")
    Institute findOneById(@Param("id") Integer id);

    @Query("SELECT i FROM Institute i WHERE i.id= :id")
    Optional<Institute> findById(@Param("id") Integer id);

    @Query("SELECT i FROM Institute i WHERE i.status= :status")
    List<Institute> findAllByStatus(@Param("status") Status status, Pageable page);

    @Query("SELECT i FROM Institute i WHERE i.status= :status")
    Page<Institute> findAllInstituteByStatus(@Param("status") Status status, Pageable page);

    @Query("SELECT i FROM Institute i WHERE i.registrationPrefix= :registrationPrefix")
    Optional<Institute> findOneByRegistrationPrefix(@Param("registrationPrefix") String registrationPrefix);

}