package com.rarait.education.config;

import com.rarait.framework.config.JavaMailConfig;
import com.rarait.framework.shared.ProfileNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Configuration
@Profile(ProfileNames.PROD)
public class EmailConfiguration extends JavaMailConfig {

    @Bean
    @Lazy
    public JavaMailSender javaMailSender() {
        return getMailSender();
    }

    @Bean
    @Lazy
    public FreeMarkerConfigurationFactory freeMarkerConfigurationFactory() {
        return getFreeMarkerConfigFactory();
    }

    @Bean
    @Lazy
    public freemarker.template.Configuration configuration() {
        return getConfiguration(this.getClass(), "/templates/");
    }
}