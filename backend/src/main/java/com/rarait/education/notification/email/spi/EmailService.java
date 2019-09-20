package com.rarait.education.notification.email.spi;

import com.rarait.education.notification.email.impl.EmailRecord;
import com.rarait.education.notification.email.resource.EmailRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface EmailService {

    EmailRecord createEmail(EmailRequest request);

    EmailRecord getEmailById(long emailId);

    EmailRecord updateProcessingStatus(long emailId);

    void updateErrorStatus(long emailId, String errorMsg);

    void updateSuccessStatus(long emailId);
}
