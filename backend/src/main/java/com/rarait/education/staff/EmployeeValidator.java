package com.rarait.education.staff;

import com.rarait.education.staff.resource.EmployeeCreateRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface EmployeeValidator {
    void validate(EmployeeCreateRequest request);
}
