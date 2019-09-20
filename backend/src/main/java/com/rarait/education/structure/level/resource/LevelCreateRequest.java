package com.rarait.education.structure.level.resource;

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
public class LevelCreateRequest implements Serializable {

    private String name;
    private String code;
    private int department;
}
