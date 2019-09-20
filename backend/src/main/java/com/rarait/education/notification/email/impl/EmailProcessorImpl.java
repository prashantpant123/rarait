package com.rarait.education.notification.email.impl;

import com.rarait.education.notification.email.resource.EmailRequest;
import com.rarait.education.notification.email.spi.EmailProcessor;
import com.rarait.education.notification.email.spi.EmailSender;
import com.rarait.education.notification.email.spi.EmailService;
import com.rarait.framework.shared.ProfileNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Profile(ProfileNames.PROD)
@Service
public class EmailProcessorImpl implements EmailProcessor {

    private final EmailService emailService;
    private final EmailSender emailSender;

    @Lazy
    @Autowired
    public EmailProcessorImpl(EmailService emailService, EmailSender emailSender) {
        this.emailService = emailService;
        this.emailSender = emailSender;
    }

    @Override
    public void sendEmail(EmailRequest request){
        EmailRecord email = emailService.createEmail(request);
        emailSender.processJob(email.getId());
    }
}