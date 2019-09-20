package com.rarait.education.summary;

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
public class SummaryResource implements Serializable {

    @JsonProperty("total_student")
    private int totalStudent;

    @JsonProperty("total_teacher")
    private int totalTeacher;
}
