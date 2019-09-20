package com.rarait.education.staff.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class EmployeeDetailResource extends EmployeeCreateRequest implements Serializable {

    private long id;

    @JsonProperty("dob_bs")
    private String dobBs;

    @JsonProperty("employee_type")
    private String employeeType;

    private String gender;

    private String picture;

    private String nationality;

    @JsonProperty("blood_group")
    private String bloodGroup;
}
