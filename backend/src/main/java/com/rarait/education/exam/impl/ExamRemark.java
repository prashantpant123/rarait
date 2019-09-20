package com.rarait.education.exam.impl;

import com.rarait.education.institute.impl.Institute;
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
@Entity
@Getter
@Setter
@ToString
public class ExamRemark extends BaseEntity<Integer> {

    private String remarks;

    @ManyToOne
    private Institute institute;

}
