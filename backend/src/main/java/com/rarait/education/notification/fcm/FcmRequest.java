package com.rarait.education.notification.fcm;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Builder
@ToString
public class FcmRequest implements Serializable {

    private String fcmId;
    private String message;

}