package com.rarait.education.institute.resource;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@ToString
public class InstituteTypeResource implements Serializable {
    private String name;

    public InstituteTypeResource(String name) {
        this.name = name;
    }
}
