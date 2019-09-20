package com.rarait.education.exam.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class ExamRoutineUpdateRequest extends ExamRoutineCreateRequest {

    private int id;
}