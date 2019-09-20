package com.rarait.education.notification.fcm;

import com.rarait.education.notification.fcm.resource.FcmPushRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface FcmSender {
    void sendFcm(FcmPushRequest request);
}
