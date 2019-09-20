package com.rarait.education.document;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Builder
@Getter
@ToString
public class DocumentResource implements Serializable {

    private long id;

    private String filename;

    private String type;

}
