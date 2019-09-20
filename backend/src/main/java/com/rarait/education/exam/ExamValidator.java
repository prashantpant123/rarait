package com.rarait.education.exam;

import com.rarait.education.exam.resource.ExamCreateRequest;

public interface ExamValidator {
    void validate(ExamCreateRequest request);
}
