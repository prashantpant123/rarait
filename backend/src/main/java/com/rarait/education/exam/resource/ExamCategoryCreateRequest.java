package com.rarait.education.exam.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class ExamCategoryCreateRequest implements Serializable {

    private String name;

    private short weightage;

}
