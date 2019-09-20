package com.rarait.education.notification.sms.impl;

import com.rarait.framework.domain.Job;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Entity
@Getter
@Setter
@ToString
public class SmsRecord extends Job {

    @Column(length = 15)
    private String mobileNumber;

    @Column(length = 160)
    private String message;

    @Column(length = 50)
    private String operatorMessageId;

    private Date deliveredDate;

    private Integer institudeId;

}