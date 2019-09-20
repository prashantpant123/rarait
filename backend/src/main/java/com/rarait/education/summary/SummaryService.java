package com.rarait.education.summary;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface SummaryService {

    SummaryResource getRecord();

    void updateStudent();

    void updateTeacher();
}
