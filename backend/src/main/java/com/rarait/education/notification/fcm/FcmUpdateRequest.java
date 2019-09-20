package com.rarait.education.notification.fcm;

import com.rarait.framework.domain.JobStatus;
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
public class FcmUpdateRequest implements Serializable {

    private long id;
    private JobStatus status;
    private String message;
}