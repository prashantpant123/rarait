package com.rarait.education.structure.level.resource;

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
public class LevelRoutineCreateRequest implements Serializable {

    @JsonProperty("start_time")
    private Date startTime;

    @JsonProperty("end_time")
    private Date endTime;

    @JsonProperty("subject_id")
    private int subjectId;

    @JsonProperty("day_of_week")
    private short dayOfWeek;
}
