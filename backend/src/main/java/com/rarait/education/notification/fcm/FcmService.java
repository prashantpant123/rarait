package com.rarait.education.notification.fcm;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface FcmService {

    void createFcm(FcmRequest request);

    void updateFcm(FcmUpdateRequest request);
}
