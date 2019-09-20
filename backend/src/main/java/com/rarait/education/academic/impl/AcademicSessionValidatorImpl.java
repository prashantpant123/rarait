package com.rarait.education.academic.impl;

import com.rarait.education.academic.AcademicSessionValidator;
import com.rarait.education.academic.resource.AcademicSessionCreateRequest;
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
public class AcademicSessionValidatorImpl implements AcademicSessionValidator {

    @Override
    public void validate(AcademicSessionCreateRequest request) {
        if (InputUtil.isEmpty(request.getName())) {
            throw new ClientRestException("Academic session name must not be empty");
        } else if (InputUtil.isEmpty(request.getStartDateAD())) {
            throw new ClientRestException("Start date must not be empty");
        } else if (InputUtil.isEmpty(request.getEndDateAD())) {
            throw new ClientRestException("End date must not be empty");
        }
    }
}
