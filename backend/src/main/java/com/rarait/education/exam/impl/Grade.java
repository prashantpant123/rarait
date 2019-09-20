package com.rarait.education.exam.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.structure.level.impl.Level;
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
@Entity
@Setter
@Getter
@ToString
public class Grade extends BaseEntity<Integer> {

    private String value;

    private short lowMark;

    private short highMark;
//
//    @ManyToOne
//    private Level level;

    @ManyToOne
    private Institute institute;

    private Status status;
}
