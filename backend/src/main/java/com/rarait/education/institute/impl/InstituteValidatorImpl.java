package com.rarait.education.institute.impl;

import com.rarait.education.institute.InstituteValidator;
import com.rarait.education.institute.resource.InstituteCreateRequest;
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
public class InstituteValidatorImpl implements InstituteValidator {

    @Override
    public void validate(InstituteCreateRequest request) {
        log.debug("Institute details: {}", request.toString());
        if (InputUtil.isEmpty(request.getName())) {
            throw new ClientRestException("Institute name must not be empty or null");
        } else if (InputUtil.isEmpty(request.getAddress())) {
            throw new ClientRestException("Institute address must not be empty or null");
        } else if (InputUtil.isEmpty(request.getPrincipal())) {
            throw new ClientRestException("Principal name must not be empty or null");
        }
    }
}
