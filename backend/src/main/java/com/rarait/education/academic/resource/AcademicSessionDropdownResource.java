package com.rarait.education.academic.resource;

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
public class AcademicSessionDropdownResource implements Serializable {

    private int id;
    private String name;
}
