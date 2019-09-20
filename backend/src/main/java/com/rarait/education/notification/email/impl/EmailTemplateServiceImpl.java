package com.rarait.education.notification.email.impl;

import com.rarait.education.notification.email.spi.EmailTemplateRepository;
import com.rarait.education.notification.email.spi.EmailTemplateService;
import com.rarait.education.shared.Profiles;
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
@Service
@Profile(ProfileNames.PROD)
public class EmailTemplateServiceImpl implements EmailTemplateService {

    private final EmailTemplateRepository emailTemplateRepository;

    @Lazy
    @Autowired
    public EmailTemplateServiceImpl(EmailTemplateRepository emailTemplateRepository) {
        this.emailTemplateRepository = emailTemplateRepository;
    }

    @Override
    public EmailTemplate getEmailTemplateByName(String templateName) {
        return emailTemplateRepository.findByTemplateName(templateName);
    }
}