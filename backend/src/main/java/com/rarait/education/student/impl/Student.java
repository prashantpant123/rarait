package com.rarait.education.student.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.structure.level.impl.Level;
import com.rarait.education.shared.Gender;
import com.rarait.education.structure.level.impl.Section;
import com.rarait.education.utility.bloodgroup.BloodGroup;
import com.rarait.education.utility.address.impl.Address;
import com.rarait.education.utility.nationality.Nationality;
import com.rarait.education.utility.occupation.impl.Occupation;
import com.rarait.education.vehicle.impl.TransportRoute;
import com.rarait.framework.domain.BaseEntity;
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
@Getter
@Setter
@Entity
@ToString
public class Student extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    private Institute institute;

    @Column(nullable = false, length = 50)
    private String firstname;

    @Column(nullable = false, length = 50)
    private String lastname;

    @Column(nullable = false)
    private String fatherName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Occupation fatherOccupation;

    @Column(length = 50)
    private String fatherContact;

    @Column(nullable = false)
    private String motherName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Occupation motherOccupation;

    @Column(length = 50)
    private String motherContact;

    @Column(nullable = false)
    private String guardian;

    @Column(length = 50)
    private String guardianContact;

    @Column(nullable = false)
    private Date enrolledDate;

    private String photoPath;

    private String remarks;

    @Column(length = 150)
    private String address;

    @Column(nullable = false)
    private Gender gender;

    private Date dateOfBirthAd;

    private String dateOfBirthBs;

    @Column(unique = true, nullable = false, length = 50)
    private String registrationId;

    @Column(nullable = false, length = 50)
    private String rollNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Level level;

    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY)
    private TransportRoute transportRoute;

    private float height;

    private float weight;

    private BloodGroup bloodGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    private Nationality nationality;
}
