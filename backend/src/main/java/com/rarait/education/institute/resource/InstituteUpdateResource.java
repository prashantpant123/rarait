package com.rarait.education.institute.resource;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
public class InstituteUpdateResource extends InstituteCreateRequest {
    private int id;
}
