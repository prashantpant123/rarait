package com.rarait.education.vehicle;

import com.rarait.education.shared.PagedResponse;
import com.rarait.education.vehicle.impl.TransportRoute;
import com.rarait.education.vehicle.resource.TransportRouteCreateRequest;
import com.rarait.education.vehicle.resource.TransportRouteDropdownResource;
import com.rarait.education.vehicle.resource.TransportRouteResource;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface TransportRouteService {

    void addBusRoute(TransportRouteCreateRequest request);

    PagedResponse<TransportRouteResource> findAllRoute(int pageNumber);

    TransportRoute findOneByIdAndInstitute(int instituteId, int transportId);

    TransportRoute findOneById(int transportId);

    List<TransportRouteDropdownResource> findAllRouteForDropdown();

    TransportRouteResource findRouteDetailById(int transportRouteId);
}
