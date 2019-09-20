package com.rarait.education.institute.resource;

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
public class InstituteLoginResource implements Serializable {

    private Integer id;
    private String username;
    private String status;
}
