package com.rarait.education.staff.resource;

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
public class EmployeeCreateResponse implements Serializable {

    private long id;

    private String message;
}
