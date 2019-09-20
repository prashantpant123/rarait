package com.rarait.education.structure.level.resource;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Builder
@Getter
@ToString
public class SectionDetailResource implements Serializable {

    private String name;

    @Column(name = "class_name")
    private String level;

    private String status;
}
