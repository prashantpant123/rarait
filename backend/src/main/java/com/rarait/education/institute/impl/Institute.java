package com.rarait.education.institute.impl;

import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Entity
@Getter
@Setter
@ToString
public class Institute extends BaseEntity<Integer> {

    @Column(length = 150)
    private String name;

    @Column(length = 150)
    private String address;

    @Column(length = 50)
    private String longitude;

    @Column(length = 50)
    private String latitude;

    private String filename;

    private Status status = Status.ACTIVE;

    private String remarks;

    private String principal;

    private String landline;

    private String website;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ContactPerson> contact;

    @Column(length = 6, unique = true)
    private String registrationPrefix;
}