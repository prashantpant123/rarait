package com.rarait.education.account.payment.impl;

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
@Getter
@Setter
@ToString
@Entity
public class BillInvoiceParticular extends BaseEntity<Long> {

    private double amount;
    private String subject;

    @ManyToOne
    private BillInvoice billInvoice;
}