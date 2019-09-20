package com.rarait.education.structure.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class SubjectUpdateRequest extends SubjectCreateRequest{

    private int id;
}
