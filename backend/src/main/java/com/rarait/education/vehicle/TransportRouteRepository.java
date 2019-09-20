package com.rarait.education.vehicle;

import com.rarait.education.vehicle.impl.TransportRoute;
import com.rarait.education.vehicle.resource.TransportRouteDropdownResource;
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
public interface TransportRouteRepository extends BaseJpaRepository<TransportRoute, Integer> {

    @Query("SELECT t FROM TransportRoute t WHERE t.status= :status ORDER BY t.route")
    List<TransportRoute> findAllByStatus(@Param("status") Status status);

    @Query("SELECT t FROM TransportRoute t WHERE t.id= :routeId AND t.transport.institute.id= :instituteId")
    Optional<TransportRoute> findOneByIdAndInstitute(@Param("instituteId") int instituteId,
                                                     @Param("routeId") int routeId);

    @Query("SELECT t FROM TransportRoute t WHERE t.status= :status AND t.transport.institute.id= :instituteId")
    Page<TransportRoute> findAllByStatusAndInstitute(@Param("status") Status status,
                                                     @Param("instituteId") int instituteId,
                                                     Pageable pageable);
}