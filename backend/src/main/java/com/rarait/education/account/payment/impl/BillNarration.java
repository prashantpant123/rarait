package com.rarait.education.account.payment.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Setter
@Getter
@ToString
@Entity
public class BillNarration extends BaseEntity<Integer> {

    private String display;
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Institute institute;
}
