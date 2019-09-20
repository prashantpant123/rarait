package com.rarait.education.structure.resource;

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
public class SubjectCreateRequest implements Serializable {

    private String name;
    private String code;

    private String description;

    @JsonProperty("level_id")
    private int levelId;

    @JsonProperty("full_mark")
    private short fullMark;

    @JsonProperty("pass_mark")
    private short passMark;

    private boolean optional;

    private boolean practical;

    @JsonProperty("practical_full_mark")
    private short practicalFullMark;

    @JsonProperty("practical_pass_mark")
    private short practicalPassMark;
}