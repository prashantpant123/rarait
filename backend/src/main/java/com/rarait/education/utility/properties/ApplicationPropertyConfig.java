package com.rarait.education.utility.properties;

import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@Entity
@ToString
public class ApplicationPropertyConfig extends BaseEntity<Short> {

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @Column(name = "[value]")
    private String value;

    private String description;

    private Status status;
}
