package com.rarait.education.exam.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Builder
@Getter
@ToString
public class ExamDetailResource implements Serializable {

    private String name;

    @JsonProperty("academic_session")
    private String academicSession;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    private short weightage;

    @JsonProperty("start_date_bs")
    private String startDateBs;

    @JsonProperty("end_date_bs")
    private String endDateBs;
}
