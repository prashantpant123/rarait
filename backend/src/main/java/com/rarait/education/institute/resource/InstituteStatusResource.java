package com.rarait.education.institute.resource;

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
public class InstituteStatusResource implements Serializable{

    private int id;
    private String status;
    private String remarks;
}
