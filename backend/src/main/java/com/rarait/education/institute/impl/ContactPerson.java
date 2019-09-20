package com.rarait.education.institute.impl;

import com.rarait.framework.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Entity
@Getter
@Setter
@ToString
public class ContactPerson extends BaseEntity<Integer> {

    @Column(nullable = false, length = 50)
    private String fullname;

    @Column(length = 13)
    private String mobileNumber;

    @Column(length = 50)
    private String emailId;

    @Column(length = 13)
    private String landline;

    @Column(length = 50)
    private String designation;
}
