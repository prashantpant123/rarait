package com.rarait.education.academic.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AcademicSessionResource extends AcademicSessionCreateRequest implements Serializable {

    private int id;

    @JsonProperty("start_date_bs")
    private String startDateBS;

    @JsonProperty("end_date_bs")
    private String endDateBS;
}
