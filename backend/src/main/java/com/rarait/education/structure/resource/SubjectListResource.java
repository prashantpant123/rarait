package com.rarait.education.structure.resource;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Builder
@ToString
public class SubjectListResource implements Serializable {

    private short levelId;
    private String level;

    private List<SubjectResource> subjects;
}
