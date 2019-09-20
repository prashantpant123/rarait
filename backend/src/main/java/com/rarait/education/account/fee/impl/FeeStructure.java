package com.rarait.education.account.fee.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.structure.level.impl.Level;
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
public class FeeStructure extends BaseEntity<Integer> {

    @ManyToOne
    private Level level;

    @ManyToOne
    private Institute institute;

    private boolean discountable;

    private boolean taxable;

    private short discountValue;

    private double totalAmount;

    private short taxValue;

    private Status status;

    private String title;

    private String description;
}