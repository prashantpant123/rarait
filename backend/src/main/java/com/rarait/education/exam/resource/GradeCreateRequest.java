package com.rarait.education.exam.resource;

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
public class GradeCreateRequest implements Serializable {

    private String value;

    @JsonProperty("high_mark")
    private short highMark;

    @JsonProperty("low_mark")
    private short lowMark;

}
