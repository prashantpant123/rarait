package com.rarait.education.notification.email.spi;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface EmailSender {

    void processJob(long emailId);
}
