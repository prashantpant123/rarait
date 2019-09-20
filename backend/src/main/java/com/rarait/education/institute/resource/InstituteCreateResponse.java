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
public class InstituteCreateResponse implements Serializable {

    private int id;

    private String message;
}
