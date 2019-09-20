package com.rarait.education.exam.result;

import com.rarait.education.exam.impl.ExamRoutine;
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
public class ExamResult extends BaseEntity<Long> {

    @ManyToOne
    private ExamRoutine examRoutine;

    private short obtainMark;

    private ExamResultStatus status;

    private String remarks;
}
