package com.rarait.education.notification.sms.impl;

import com.rarait.education.notification.sms.SmsSender;
import com.rarait.education.shared.Profiles;
import com.rarait.framework.shared.ProfileNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
@Profile(ProfileNames.PROD)
public class SmsSenderImpl implements SmsSender {

}