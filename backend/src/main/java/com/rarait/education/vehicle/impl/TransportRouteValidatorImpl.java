package com.rarait.education.vehicle.impl;

import com.rarait.education.vehicle.TransportRouteValidator;
import com.rarait.education.vehicle.resource.TransportRouteCreateRequest;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.InputUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Component
public class TransportRouteValidatorImpl implements TransportRouteValidator {

    @Override
    public void validateBusRoute(TransportRouteCreateRequest request) {
        if (InputUtil.isEmpty(request.getRoute())) {
            throw new ClientRestException("Transport route must not be empty or null");
        } else if (InputUtil.isEmpty(request.getPickupTime())) {
            throw new ClientRestException("Transport pickup time must not be empty or null");
        } else if (InputUtil.isEmpty(request.getDropTime())) {
            throw new ClientRestException("Transport drop time must not be empty or null");
        }else if(InputUtil.isEmpty(request.getTransportId())){
            throw new ClientRestException("Transport must be selected");
        }
    }
}
