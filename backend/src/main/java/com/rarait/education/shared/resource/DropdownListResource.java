package com.rarait.education.shared.resource;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Builder
@ToString
public class DropdownListResource implements Serializable {
    private int id;
    private String name;
}
