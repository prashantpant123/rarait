package com.rarait.education.notification.sms.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class SmsRequest implements Serializable {

    private String mobileNumber;

    private String message;

    private int senderUserId;
}
