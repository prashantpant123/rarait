package com.rarait.education.staff.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class EmployeeCreateRequest implements Serializable {

    @JsonProperty("employee_id")
    private String employeeId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("gender_id")
    private int genderId;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("current_address")
    private String currentAddress;

    @JsonProperty("permanent_address")
    private String permanentAddress;

    @JsonProperty("joining_date")
    private Date joiningDate;

    @JsonProperty("primary_contact")
    private String primaryContact;

    @JsonProperty("secondary_contact")
    private String secondaryContact;

    @JsonProperty("type_id")
    private int typeId;

    @JsonProperty("blood_group_id")
    private int bloodGroupId;

    @JsonProperty("nationality_id")
    private short nationalityId;

    @JsonProperty("experience_summary")
    private String experienceSummary;

    @JsonProperty("marital_status")
    private int maritalStatus;

    private String designation;

    private String qualification;

}
