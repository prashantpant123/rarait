package com.rarait.education.structure.level.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.structure.department.impl.DepartmentType;
import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Setter
@Getter
@Entity
@ToString
public class Level extends BaseEntity<Integer>{

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    private Institute institute;

    private Status status;

    private DepartmentType department;
}