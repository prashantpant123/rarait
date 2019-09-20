package com.rarait.education.notification.fcm.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rarait.education.notification.fcm.FcmConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Builder
@ToString
public class FcmPushRequest implements Serializable {

    @JsonProperty("data")
    private FcmContent content;

    @JsonProperty("content_available")
    private boolean contentAvailable;
}
