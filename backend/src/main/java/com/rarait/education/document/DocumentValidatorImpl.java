package com.rarait.education.document;

import com.rarait.education.exam.resource.ExamCreateRequest;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.InputUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Component
public class DocumentValidatorImpl implements DocumentValidator {

    @Override
    public void validate(String filename) {
        if (InputUtil.isEmpty(filename)) {
            throw new ClientRestException("File name must not be empty");
        } else if (InputUtil.isEmpty(FilenameUtils.getExtension(filename))) {
            throw new ClientRestException("File extension is empty or missing");
        }
    }
}
