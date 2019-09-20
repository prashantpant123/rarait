package com.rarait.education.structure.subject.impl;

import com.rarait.education.structure.resource.SubjectCreateRequest;
import com.rarait.education.structure.subject.SubjectValidator;
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
public class SubjectValidatorImpl implements SubjectValidator {

    @Override
    public void validate(SubjectCreateRequest request) {
        if (InputUtil.isEmpty(request.getCode())) {
            throw new ClientRestException("Invalid subject code");
        } else if (InputUtil.isEmpty(request.getName())) {
            throw new ClientRestException("Invalid subject name");
        } else if (InputUtil.isEmpty(request.getLevelId())) {
            throw new ClientRestException("Subject must be assigned to grade/level");
        } else if (InputUtil.isEmpty(request.getPassMark())) {
            throw new ClientRestException("Pass mark is empty");
        } else if (InputUtil.isEmpty(request.getFullMark())) {
            throw new ClientRestException("Full mark is empty");
        } else if (InputUtil.isEmpty(request.isPractical())) {
            throw new ClientRestException("Practical is required");
        } else if (InputUtil.isEmpty(request.isOptional())) {
            throw new ClientRestException("Optional is required");
        } else if (request.isPractical()) {
            if (InputUtil.isEmpty(request.getPracticalFullMark())) {
                throw new ClientRestException("Practical full mark is empty");
            } else if (InputUtil.isEmpty(request.getPracticalPassMark())) {
                throw new ClientRestException("Practical pass mark is empty");
            } else if (request.getPracticalFullMark() <= 0) {
                throw new ClientRestException("Practical full mark cannot be zero or negative");
            } else if (request.getPracticalPassMark() <= 0) {
                throw new ClientRestException("Practical pass mark cannot be zero or negative");
            }
        }
    }
}
