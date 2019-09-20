package com.rarait.education.exam.result;

import com.rarait.education.exam.impl.Exam;
import com.rarait.education.student.impl.Student;
import com.rarait.framework.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
@Entity
public class ExamResultSummary extends BaseEntity<Long> {

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Student student;

    private short totalMark;

    private short obtainedTotalMark;

    private ExamResultStatus status;
}