package com.rarait.education.exam.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Builder
@Getter
@ToString
public class ExamRoutineResource implements Serializable {

    private int id;

    @JsonProperty("subject_id")
    private int subjectId;

    private String subject;

    @JsonProperty("full_mark")
    private short fullMarks;

    @JsonProperty("pass_mark")
    private short passMarks;

    @JsonProperty("exam_date")
    private Date examDate;

    @JsonProperty("start_time")
    private Date startTime;

    @JsonProperty("end_time")
    private Date endTime;

    private String remarks;
}
