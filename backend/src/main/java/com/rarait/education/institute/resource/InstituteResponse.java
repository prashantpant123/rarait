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
public class InstituteResponse implements Serializable{

    private String name;
    private String address;
    private String type;
    private int id;
}