package com.rarait.education.account.payment.impl;

import com.rarait.education.account.fee.impl.FeeStructure;
import com.rarait.education.login.impl.User;
import com.rarait.framework.domain.BaseEntity;
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
@Getter
@Setter
@ToString
@Entity
public class BillInvoice extends BaseEntity<Long> {

    private String invoiceId;

    @ManyToOne
    private User receivedBy;

    @ManyToOne
    private User paidBy;

    private BillInvoiceStatus status;

    @Column(length = 4)
    private short fiscalYear;
}