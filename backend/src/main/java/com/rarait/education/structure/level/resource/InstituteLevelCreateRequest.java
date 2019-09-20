package com.rarait.education.structure.level.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Setter
@Getter
@ToString
@Deprecated
public class InstituteLevelCreateRequest implements Serializable {

    private short levelId;
    private String section;
}
