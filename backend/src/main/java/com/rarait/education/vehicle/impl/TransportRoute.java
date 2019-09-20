package com.rarait.education.vehicle.impl;

import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since Sep 28, 2018
 */
@Setter
@Getter
@Entity
@ToString
public class TransportRoute extends BaseEntity<Integer> {

    @ManyToOne
    private Transport transport;

    private String route;

    @Column(length = 10)
    private String pickupTime;

    @Column(length = 10)
    private String dropTime;

    private Status status = Status.ACTIVE;
}