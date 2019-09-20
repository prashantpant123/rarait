package com.rarait.education.exam.validator;

import com.rarait.education.exam.ExamValidator;
import com.rarait.education.exam.resource.ExamCreateRequest;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.InputUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExamValidatorImpl implements ExamValidator {

    @Override
    public void validate(ExamCreateRequest request) {
        if (InputUtil.isEmpty(request.getTermId())) {
            throw new ClientRestException("Term end must be selected");
        } else if (InputUtil.isEmpty(request.getStartDate())) {
            throw new ClientRestException("Request failed, exam start date is required");
        } else if (InputUtil.isEmpty(request.getEndDate())) {
            throw new ClientRestException("Request failed, exam end date is  required");
        } else if (InputUtil.isEmpty(request.getAcademicSessionId())) {
            throw new ClientRestException("Request failed, academic session is required");
        }
    }
}