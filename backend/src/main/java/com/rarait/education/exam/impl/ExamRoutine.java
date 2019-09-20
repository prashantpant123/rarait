package com.rarait.education.exam.impl;

import com.rarait.education.structure.subject.impl.Subject;
import com.rarait.education.structure.level.impl.Level;
import com.rarait.framework.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@Entity
@ToString
public class ExamRoutine extends BaseEntity<Integer> {

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Level level;

    private Date examDate;

    @Column(length = 10)
    private Time startTime;

    @Column(length = 10)
    private Time endTime;

    private short fullMark;
    private short passMark;

    private String remarks;
}
