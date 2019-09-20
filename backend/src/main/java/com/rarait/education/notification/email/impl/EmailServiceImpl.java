package com.rarait.education.notification.email.impl;

import com.rarait.education.notification.email.resource.EmailRequest;
import com.rarait.education.notification.email.spi.EmailRepository;
import com.rarait.education.notification.email.spi.EmailService;
import com.rarait.education.notification.email.spi.EmailTemplateService;
import com.rarait.education.shared.Profiles;
import com.rarait.framework.domain.JobStatus;
import com.rarait.framework.shared.ProfileNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
@Profile(ProfileNames.PROD)
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final EmailTemplateService emailTemplateService;

    @Lazy
    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository,
                            EmailTemplateService emailTemplateService) {
        this.emailRepository = emailRepository;
        this.emailTemplateService = emailTemplateService;
    }

    @Override
    public EmailRecord createEmail(EmailRequest request) {
        EmailRecord email = new EmailRecord();
        email.setEmailId(request.getReceiver());
        email.setStatus(JobStatus.QUEUED);
        email.setTemplate(emailTemplateService.getEmailTemplateByName(request.getTemplate()));
        email.setProperties(request.getContent());
        return emailRepository.save(email);
    }

    @Override
    public EmailRecord getEmailById(long emailId) {
        return emailRepository.findById(emailId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EmailRecord updateProcessingStatus(long emailId) {
        EmailRecord email = emailRepository.findById(emailId);
        email.setAttempts(email.getAttempts() + 1);
        email.setStatus(JobStatus.RUNNING);
        email.setExecutionTime(new Date());
        return emailRepository.save(email);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateErrorStatus(long emailId,
                                  String errorMsg) {
        EmailRecord email = emailRepository.findById(emailId);
        email.setAttempts(email.getAttempts() + 1);
        email.setStatus(JobStatus.FAILED);
        email.setLastError(errorMsg);
        emailRepository.save(email);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateSuccessStatus(long emailId) {
        EmailRecord email = emailRepository.findById(emailId);
        email.setAttempts(email.getAttempts() + 1);
        email.setStatus(JobStatus.COMPLETED);
        email.setFinishedTime(new Date());
        emailRepository.save(email);
    }
}