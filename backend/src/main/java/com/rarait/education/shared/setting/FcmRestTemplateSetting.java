package com.rarait.education.shared.setting;

import com.rarait.framework.setting.BaseRestTemplateSetting;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Component(value = "fcmRestTemplateSetting")
@ConfigurationProperties("resttemplate.fcm")
public class FcmRestTemplateSetting extends BaseRestTemplateSetting {
}
