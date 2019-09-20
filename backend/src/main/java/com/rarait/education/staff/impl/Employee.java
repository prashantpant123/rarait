package com.rarait.education.staff.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.shared.Gender;
import com.rarait.education.utility.bloodgroup.BloodGroup;
import com.rarait.education.utility.address.impl.Address;
import com.rarait.education.utility.nationality.Nationality;
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
@Entity
@Getter
@Setter
@ToString
public class  Employee extends BaseEntity<Long> {

    @Column(length = 50)
    private String employeeId;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    private Gender gender;

    private Date dobAd;

    private String dobBs;

    private String currentAddress;

    private String permanentAddress;

    @Column(nullable = false)
    private Date joiningDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Institute institute;

    private EmployeeType type;

    private String qualification;

    private String experienceSummary;

    private String designation;

    private String primaryContact;

    private String secondaryContact;

    private BloodGroup bloodGroup;

    private MaritalStatus maritalStatus;

    @Column(length = 100)
    private String fatherName;

    @Column(length = 100)
    private String motherName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Nationality nationality;
}
