package com.rarait.education.vehicle;

import com.rarait.education.vehicle.resource.TransportRouteCreateRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface TransportRouteValidator {

    void validateBusRoute(TransportRouteCreateRequest request);
}
