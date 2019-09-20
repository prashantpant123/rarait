package com.rarait.education.utility.properties;

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
public class AppPropCreateRequest implements Serializable{

    private PropertyType type;
    private String value;
    private String description;
}
