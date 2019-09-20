package com.rarait.education.utility.occupation.impl;

import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@Entity
@ToString
public class Occupation extends BaseEntity<Short> {

    private String name;

    private Status status;
}