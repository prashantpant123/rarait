package com.rarait.education.account.fee.impl;

import com.rarait.education.account.fee.FeeStructureValidator;
import com.rarait.education.account.fee.resource.FeeStructureCreateRequest;
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
public class FeeStructureValidatorImpl implements FeeStructureValidator {

    @Override
    public void validate(FeeStructureCreateRequest request) {
        if (InputUtil.isEmpty(request.getTitle())) {
            throw new ClientRestException("Fee title must not be empty");
        } else if (InputUtil.isEmpty(request.getAmount()) || request.getAmount() <= 0) {
            throw new ClientRestException("Fee amount must not be empty");
        }
    }
}
