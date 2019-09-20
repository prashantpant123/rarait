package com.rarait.education.staff.impl;

import com.rarait.education.staff.EmployeeValidator;
import com.rarait.education.staff.resource.EmployeeCreateRequest;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.InputUtil;
import com.rarait.framework.shared.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Component
public class EmployeeValidatorImpl implements EmployeeValidator {

    @Override
    public void validate(EmployeeCreateRequest request) {

        if (InputUtil.isEmpty(request.getEmployeeId())) {
            throw new ClientRestException("Employee Id cannot be empty");
        } else if (InputUtil.isEmpty(request.getFirstName()) || InputUtil.isEmpty(request.getLastName())) {
            throw new ClientRestException("Employee name cannot be empty");
        } else if (InputUtil.isEmpty(request.getTypeId()) || request.getTypeId() == 0) {
            throw new ClientRestException("Employee type must be selected");
        } else if (InputUtil.isEmpty(request.getGenderId()) || request.getGenderId() == 0) {
            throw new ClientRestException("Employee gender must be selected");
        } else if (InputUtil.isEmpty(request.getJoiningDate())) {
            throw new ClientRestException("Employee joining date is required");
        } else if (InputUtil.isEmpty(request.getDateOfBirth())) {
            throw new ClientRestException("Employee date of birth is required");
        } else if (InputUtil.isEmpty(request.getQualification())) {
            throw new ClientRestException("Employee qualification is required");
        } else if (InputUtil.isEmpty(request.getPermanentAddress())) {
            throw new ClientRestException("Employee permanent address is required");
        }
    }
}
