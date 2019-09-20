package com.rarait.education.config;

import com.rarait.education.shared.setting.FcmRestTemplateSetting;
import com.rarait.framework.config.BaseRestTemplateConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Configuration
public class RestTemplateConfiguration extends BaseRestTemplateConfig {

    //5 secs
    private static final int VALIDATE = 5000;
    private static final int MIN = 0;
    // 5 mins
    private static final int MAX = 300000;
    private static final int POOL = 3000;

    private static final int RETRY = 2;

    @Bean(name = "fcmRestTemplate")
    public RestTemplate fcmTemplate(
            @Qualifier(value = "fcmRestTemplateSetting") FcmRestTemplateSetting setting) {
        return initRestTemplate(setting);
    }

}