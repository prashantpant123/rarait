package com.rarait.education.attendance.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Setter
@Getter
@ToString
public class AttendanceResponse implements Serializable {

    private long id;

    @JsonProperty("full_name")
    private String studentName;

    @JsonProperty("roll_number")
    private String rollNumber;

    @JsonProperty("student_id")
    private long studentId;

    private int status;

    private String remarks;

    public AttendanceResponse(BigInteger id,
                              String studentName,
                              String rollNumber,
                              BigInteger studentId,
                              Integer status,
                              String remarks) {
        this.id = (id == null ? 0 : id.longValue());
        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.studentId = (studentId == null ? 0 : studentId.longValue());
        this.status = (status == null ? 0 : status);
        this.remarks = remarks;
    }

    public AttendanceResponse() {
    }
}
