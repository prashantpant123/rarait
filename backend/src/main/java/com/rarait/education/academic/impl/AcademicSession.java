package com.rarait.education.academic.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Entity
@Setter
@Getter
@ToString
public class AcademicSession extends BaseEntity<Integer> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date startDateAd;

    @Column(nullable = false)
    private Date endDateAd;

    private String startDateBs;

    private String endDateBs;

    private boolean currentSession;

    @ManyToOne(fetch = FetchType.LAZY)
    private Institute institute;

    private Status status = Status.ACTIVE;
}
