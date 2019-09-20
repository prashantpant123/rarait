package com.rarait.education.vehicle;

import com.rarait.education.vehicle.impl.Transport;
import com.rarait.framework.repository.BaseJpaRepository;
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
public interface TransportRepository extends BaseJpaRepository<Transport, Integer> {

    @Query("SELECT b FROM Transport b WHERE b.institute.id= :institute")
    Page<Transport> findAllByInstitute(@Param("institute") int institute,
                                       Pageable pageable);

    @Query("SELECT t FROM Transport t WHERE t.id= :transportId AND t.institute.id= :instituteId")
    Optional<Transport> findOneByInstituteAndId(@Param("transportId") Integer transportId,
                                   @Param("instituteId") Integer instituteId);

    @Query("SELECT b FROM Transport b WHERE b.institute.id= :institute ORDER BY b.name")
    List<Transport> findAllByInstitute(@Param("institute") int institute);
}
