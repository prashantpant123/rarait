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
public class LevelDetailResource implements Serializable {

    private String name;

    private String code;

    @JsonProperty("academic_session")
    private String academicSession;

    private String department;
}
