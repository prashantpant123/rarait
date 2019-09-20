package com.rarait.education.attendance;

import com.rarait.education.attendance.resource.AttendanceCreateRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface AttendanceValidator {
    void validate(AttendanceCreateRequest request);
}
