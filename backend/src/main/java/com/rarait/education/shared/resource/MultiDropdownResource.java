package com.rarait.education.shared.resource;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@ToString
@Getter
@Builder
public class MultiDropdownResource implements Serializable {

    private String label;
    private String value;
    private boolean isLeaf;
    private List<MultiDropdownResource> children;
    
}
