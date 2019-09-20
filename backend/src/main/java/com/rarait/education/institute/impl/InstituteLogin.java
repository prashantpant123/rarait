package com.rarait.education.institute.impl;

import com.rarait.education.login.impl.User;
import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@Entity
@ToString
public class InstituteLogin extends BaseEntity<Integer> {

    @ManyToOne
    private Institute institute;

    @OneToOne
    private User user;

    private Status status;
}