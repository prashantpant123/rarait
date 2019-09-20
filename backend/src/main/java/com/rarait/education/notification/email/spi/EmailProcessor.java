package com.rarait.education.notification.email.spi;

import com.rarait.education.notification.email.resource.EmailRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface EmailProcessor {

    void sendEmail(EmailRequest request);
}