package com.rarait.education.notification.email.impl;

import com.rarait.education.utility.annotation.Email;
import com.rarait.framework.domain.Job;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Map;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Setter
@Getter
@Entity
@ToString(callSuper = true)
public class EmailRecord extends Job {

    @Email
    @Column(nullable = false)
    private String emailId;

    @ManyToOne(fetch = FetchType.LAZY)
    private EmailTemplate template;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @CollectionTable(name = "email_properties", joinColumns = @JoinColumn(name = "email_id"))
    @MapKeyColumn(name = "property_key")
    @Column(name = "property_value")
    private Map<String, String> properties;
}