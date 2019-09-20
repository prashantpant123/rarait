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
@ToString
public class AcademicSessionCreateRequest implements Serializable {

    private String name;

    @JsonProperty("start_date_ad")
    private Date startDateAD;

    @JsonProperty("end_date_ad")
    private Date endDateAD;

    @JsonProperty("current_session")
    private boolean currentSession;
}
