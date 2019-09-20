package com.rarait.education.structure.level.impl;

import com.rarait.education.structure.level.LevelValidator;
import com.rarait.education.structure.level.resource.LevelCreateRequest;
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
public class LevelValidatorImpl implements LevelValidator {

    @Override
    public void validate(LevelCreateRequest request){
        if(InputUtil.isEmpty(request.getName())){
            throw new ClientRestException("Class name must not be empty");
        }
        if(InputUtil.isEmpty(request.getCode())){
            throw new ClientRestException("Class code must not be empty");
        }
    }
}
