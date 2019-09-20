package com.rarait.education.notification.sms;

import com.rarait.education.notification.sms.impl.SmsRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface SmsService {
    void addSmsRecord(SmsRequest request);
}
