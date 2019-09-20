package com.rarait.education.exam.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Builder
@Getter
@ToString
public class GradeDetailResource implements Serializable {

    private int id;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("last_modified_date")
    private Date lastModifiedDate;

    private String value;

    @JsonProperty("low_mark")
    private short lowMark;

    @JsonProperty("high_mark")
    private short highMark;


}
