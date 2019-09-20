package com.rarait.education.notification.email.impl;

import com.rarait.education.notification.email.spi.EmailSender;
import com.rarait.education.notification.email.spi.EmailService;
import com.rarait.education.shared.AppProperties;
import com.rarait.education.shared.Profiles;
import com.rarait.framework.domain.JobStatus;
import com.rarait.framework.shared.ProfileNames;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.InternetAddress;
import java.io.IOException;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Profile(ProfileNames.PROD)
@Component
public class EmailSenderJob implements EmailSender {

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;
    private final EmailService emailService;

    @Value(AppProperties.EMAIL_SENDER)
    private String sender;

    @Value(AppProperties.EMAIL_SENDER_ADDRESS)
    private String senderMail;

    @Lazy
    @Autowired
    public EmailSenderJob(JavaMailSender javaMailSender,
                          Configuration configuration,
                          EmailService emailService) {
        this.javaMailSender = javaMailSender;
        this.configuration = configuration;
        this.emailService = emailService;
    }

    @Async
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processJob(long emailId) {
        EmailRecord email = emailService.getEmailById(emailId);
        if (email != null && email.getStatus().equals(JobStatus.QUEUED)) {
            log.info("Preparing to send email {} ", email.toString());
            sendMail(emailService.updateProcessingStatus(email.getId()));
        }
    }

    private void sendMail(final EmailRecord email) {
//        email.getProperties().put(AppProperty.LOGO, imageUrl);
//        email.getProperties().put(AppProperty.WEB_HOST, imageUrl);
//        email.getProperties().put(AppProperty.CDN_HOST, cdnUrl);
        email.getProperties().put(AppProperties.TEMPLATE_NAME, email.getTemplate().getName() + ".ftl");
        try {
            if (email.getStatus().equals(JobStatus.RUNNING)) {
                configuration.getTemplateLoader().findTemplateSource(AppProperties.EMAIL_TEMPLATE);
                configuration.setDefaultEncoding("UTF-8");

                Template template = configuration.getTemplate(AppProperties.EMAIL_TEMPLATE);
                final String content = FreeMarkerTemplateUtils.processTemplateIntoString(
                        template,
                        email.getProperties()
                );

                MimeMessagePreparator mail = mimeMessage -> {
                    InternetAddress address = new InternetAddress(senderMail, sender);
                    mimeMessage.setSender(address);
                    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                    messageHelper.setFrom(address);
                    messageHelper.setTo(new InternetAddress(email.getEmailId(), email.getEmailId()));
                    messageHelper.setSubject(email.getTemplate().getSubject());
                    messageHelper.setText(content, true);

//                    if (email.getAttachment() != null) {
//                        messageHelper.addAttachment(email.getProperties().get("attachmentName"), new ByteArrayResource(Base64.decodeBase64(email.getAttachment())));
//                    }
                };
                javaMailSender.send(mail);

                log.info("Successfully sent email:  {}", email.toString());
                emailService.updateSuccessStatus(email.getId());
            }
        } catch (IOException | TemplateException | MailException exp) {
            log.error("Error on send mail: {}", exp);
            emailService.updateErrorStatus(email.getId(), exp.getMessage());
        }
    }
}