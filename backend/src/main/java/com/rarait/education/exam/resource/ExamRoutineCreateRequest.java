package com.rarait.education.exam.resource;

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
public class ExamRoutineCreateRequest implements Serializable {

    private int id;

    @JsonProperty("subject_id")
    private int subjectId;

    @JsonProperty("exam_date")
    private Date examDate;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;

    private String remarks;

    @JsonProperty("full_mark")
    private short fullMark;

    @JsonProperty("pass_mark")
    private short passMark;

}