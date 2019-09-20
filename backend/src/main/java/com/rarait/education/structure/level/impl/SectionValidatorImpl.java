package com.rarait.education.structure.level.impl;

import com.rarait.education.structure.level.SectionValidator;
import com.rarait.education.structure.level.resource.SectionCreateRequest;
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
public class SectionValidatorImpl implements SectionValidator {

    @Override
    public void validate(SectionCreateRequest request){
        if(InputUtil.isEmpty(request.getLevelId())){
            throw new ClientRestException("Class must not be empty");
        }
    }

}
