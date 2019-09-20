package com.rarait.education.exam.validator;

import com.rarait.education.exam.GradeValidator;
import com.rarait.education.exam.resource.GradeCreateRequest;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.InputUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class GradeValidatorImpl implements GradeValidator {

    @Override
    public void validate(GradeCreateRequest request){
        if(InputUtil.isEmpty(request.getValue())){
            throw new ClientRestException("Gradle value must not be empty");
        } else if (InputUtil.isEmpty(request.getHighMark())){
            throw new ClientRestException("Gradle high mark must not be empty");
        } else if (InputUtil.isEmpty(request.getLowMark())){
            throw new ClientRestException("Gradle low mark must not be empty");
        }
    }
}
