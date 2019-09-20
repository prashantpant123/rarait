package com.rarait.education.notification.fcm;

import com.rarait.education.institute.impl.Institute;
import com.rarait.framework.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
@Entity
public class FcmConfig extends BaseEntity<Integer> {

    private String fcmKey;

    @OneToOne
    private Institute institute;
}
