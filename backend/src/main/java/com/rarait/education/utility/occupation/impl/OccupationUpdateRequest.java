package com.rarait.education.utility.occupation.impl;

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
public class OccupationUpdateRequest extends OccupationCreateRequest {

    private short id;
}
