package com.rarait.education.vehicle.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.framework.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

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
public class Transport extends BaseEntity<Integer> {

    @Column(length = 50)
    private String name;

    @Column(length = 25)
    private String numberPlate;

    private Short capacity;

    @Column(length = 20)
    private String modelNumber;

    @ManyToOne
    private Institute institute;
}
