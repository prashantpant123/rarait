package com.rarait.education.structure.level.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class SectionResource implements Serializable {

    private int id;

    @JsonProperty("class_name")
    private String level;
    private String name;
    private String status;

}
