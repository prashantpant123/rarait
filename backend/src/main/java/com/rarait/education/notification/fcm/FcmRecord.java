package com.rarait.education.notification.fcm;

import com.rarait.framework.domain.Job;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
@Entity
public class FcmRecord extends Job{

    @Column(length = 100)
    private String fcmId;

    private String message;

    private Integer userId;

    private String fcmMessageId;
}
