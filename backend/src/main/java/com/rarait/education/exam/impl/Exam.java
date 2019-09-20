package com.rarait.education.exam.impl;

import com.rarait.education.academic.impl.AcademicSession;
import com.rarait.framework.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@Entity
@ToString
public class Exam extends BaseEntity<Integer> {

    @ManyToOne(fetch = FetchType.LAZY)
    private ExamTerm category;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    private String startDateBs;

    private String endDateBs;

    private short weightage;

    @ManyToOne(fetch = FetchType.LAZY)
    private AcademicSession academicSession;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Institute institute;
}