package com.rarait.education.structure.level.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class SectionCreateRequest implements Serializable {

    @JsonProperty("class_id")
    private int levelId;

    private String name;
}
