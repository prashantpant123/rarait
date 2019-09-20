package com.rarait.education.structure.department.impl;

import com.rarait.framework.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Entity
@Getter
@Setter
@ToString
public class Department extends BaseEntity<Integer>{

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private DepartmentType type;
}
