package com.rarait.education.exam.result;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public enum ExamResultStatus implements Serializable {
    PASSED,
    FAILED,
    DISQUALIFIED,
    ABSENT;
}