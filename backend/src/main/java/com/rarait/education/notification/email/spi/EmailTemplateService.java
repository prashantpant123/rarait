package com.rarait.education.notification.email.spi;

import com.rarait.education.notification.email.impl.EmailTemplate;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface EmailTemplateService {

    EmailTemplate getEmailTemplateByName(String templateName);
}