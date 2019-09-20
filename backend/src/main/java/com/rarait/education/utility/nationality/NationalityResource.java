package com.rarait.education.utility.nationality;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@ToString
@Builder
public class NationalityResource implements Serializable {

    private String name;
}
