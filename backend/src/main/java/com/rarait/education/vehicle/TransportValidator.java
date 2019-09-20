package com.rarait.education.vehicle;

import com.rarait.education.vehicle.resource.TransportCreateRequest;
import com.rarait.education.vehicle.resource.TransportRouteCreateRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface TransportValidator {

    void validateBus(TransportCreateRequest request);
}
