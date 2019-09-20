package com.rarait.education.utility.occupation.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since Aug 28, 2018
 */
@Getter
@ToString
@Builder
public class OccupationResource implements Serializable{

    private Short id;

    private String name;
}
