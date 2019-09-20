package com.rarait.education.utility.occupation.impl;

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
public class OccupationCreateRequest implements Serializable {

    private String displayName;
}
