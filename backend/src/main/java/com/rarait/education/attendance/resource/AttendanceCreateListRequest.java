package com.rarait.education.attendance.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class AttendanceCreateListRequest implements Serializable {

    @JsonProperty("class_id")
    private int levelId;

    @JsonProperty("attendance_date")
    private Date attendanceDate;

    private List<AttendanceCreateRequest> data = new ArrayList<>();
}
