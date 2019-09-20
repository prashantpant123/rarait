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
@Getter
@Setter
@Entity
@ToString
public class Degree extends BaseEntity<Integer>{

    private String name;

    @Enumerated(EnumType.STRING)
    private DegreeType type;

    private Short duration;
}