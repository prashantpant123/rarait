package com.rarait.education.attendance.impl;

import com.rarait.education.attendance.AttendanceValidator;
import com.rarait.education.attendance.resource.AttendanceCreateRequest;
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
public class AttendanceValidatorImpl implements AttendanceValidator {

    @Override
    public void validate(AttendanceCreateRequest request) {
        if (InputUtil.isEmpty(request.getStudentId())) {
            throw new ClientRestException("Request failed, student id is required");
        }
    }
}
