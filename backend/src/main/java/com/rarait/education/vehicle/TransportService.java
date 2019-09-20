package com.rarait.education.vehicle;

import com.rarait.education.shared.PagedResponse;
import com.rarait.education.vehicle.impl.Transport;
import com.rarait.education.vehicle.impl.TransportRoute;
import com.rarait.education.vehicle.resource.*;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface TransportService {

    void addBus(TransportCreateRequest request);
    
    PagedResponse<TransportCreateRequest> findAllTransports(int pageNumber);

    Transport findOneById(int busRouteId);

    List<TransportDropdownResource> findAllTransportForDropdown();

    TransportDetailResource findTransportDetailById(int transportId);

}
