package com.rarait.education.attendance.resource;

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
public class AttendanceCreateRequest implements Serializable {

    private long id;

    @JsonProperty("student_id")
    private long studentId;

    private int status;

    private String remarks;
}
