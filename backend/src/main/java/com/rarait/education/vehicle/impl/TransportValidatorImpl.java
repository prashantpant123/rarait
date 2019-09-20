package com.rarait.education.vehicle.impl;

import com.rarait.education.vehicle.TransportValidator;
import com.rarait.education.vehicle.resource.TransportCreateRequest;
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
public class TransportValidatorImpl implements TransportValidator {

    @Override
    public void validateBus(TransportCreateRequest request) {
        if (InputUtil.isEmpty(request.getNumberPlate())) {
            throw new ClientRestException("Transport numberPlate must not be empty or null");
        }
    }


}
