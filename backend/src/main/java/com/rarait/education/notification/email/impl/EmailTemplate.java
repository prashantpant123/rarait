package com.rarait.education.notification.email.impl;

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
@Getter
@Setter
@Entity
@ToString
public class EmailTemplate extends BaseEntity<Short> {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String subject;
}