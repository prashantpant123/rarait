package com.rarait.education.structure.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Builder
@ToString
public class SubjectResource implements Serializable {

    private int id;

    private String code;
    private String name;

    private String description;

    @JsonProperty("level_id")
    private int levelId;

    @JsonProperty("level_name")
    private String levelName;

    @JsonProperty("pass_mark")
    private short passMark;

    @JsonProperty("full_mark")
    private short fullMark;

    private boolean optional;

    private boolean practical;

    @JsonProperty("practical_full_mark")
    private short practicalFullMark;

    @JsonProperty("practical_pass_mark")
    private short practicalPassMark;
}
