package com.rarait.education.exam.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
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
@Entity
@ToString
public class ExamTerm extends BaseEntity<Integer> {

    private String name;

    private short weightage;

    @ManyToOne
    private Institute institute;

    private Status status;
}
