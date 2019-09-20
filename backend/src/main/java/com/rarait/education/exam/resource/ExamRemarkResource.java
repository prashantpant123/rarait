package com.rarait.education.exam.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Setter
@Getter
@ToString
public class ExamRemarkResource extends ExamRemarkCreateRequest implements Serializable {
    private int id;
}
