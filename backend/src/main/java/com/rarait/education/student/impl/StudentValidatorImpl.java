package com.rarait.education.student.impl;

import com.rarait.education.student.StudentValidator;
import com.rarait.education.student.resource.StudentRegisterRequest;
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
public class StudentValidatorImpl implements StudentValidator {

    @Override
    public void validate(StudentRegisterRequest request) {
        if (InputUtil.isEmpty(request.getFirstName()) || InputUtil.isEmpty(request.getLastName())) {
            throw new ClientRestException("Student name cannot be empty");
        } else if (InputUtil.isEmpty(request.getFatherName())) {
            throw new ClientRestException("Father name cannot be empty");
        } else if (InputUtil.isEmpty(request.getMotherName())) {
            throw new ClientRestException("Mother name cannot be empty");
        } else if (InputUtil.isEmpty(request.getAddress())) {
            throw new ClientRestException("Address name cannot be empty");
        } else if (InputUtil.isEmpty(request.getRegistrationNumber())) {
            throw new ClientRestException("Registration number cannot be empty");
        }
    }
}
