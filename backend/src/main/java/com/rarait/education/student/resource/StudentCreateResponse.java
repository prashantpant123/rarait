package com.rarait.education.student.resource;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Builder
@ToString
public class StudentCreateResponse implements Serializable {

    private long id;

    private String message;
}
