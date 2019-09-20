package com.rarait.education.utility.token.impl;

import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@Entity
@ToString
public class Token extends BaseEntity<Long> {

    private String value;

    private Date expiryDate;

    private Status status;

    private TokenType type;

    private Long customerId;
}