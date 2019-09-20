package com.rarait.education.exam.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class ExamRoutineCreateList implements Serializable {

    @JsonProperty("exam_id")
    private int examId;

    @JsonProperty("class_id")
    private int levelId;

    private List<ExamRoutineCreateRequest> data = new ArrayList<>();
}
