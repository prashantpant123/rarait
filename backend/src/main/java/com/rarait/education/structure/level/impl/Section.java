package com.rarait.education.structure.level.impl;

import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
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
public class Section extends BaseEntity<Integer> {

    @ManyToOne
    private Level level;

    @Column(length = 25)
    private String name;

    private Status status;
}
