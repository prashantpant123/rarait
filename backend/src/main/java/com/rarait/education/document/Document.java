package com.rarait.education.document;

import com.rarait.education.institute.impl.Institute;
import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Setter
@Getter
@Entity
@ToString
public class Document extends BaseEntity<Long> {

    private String filename;

    @ManyToOne
    private Institute institute;

    private Status status;

    private DocumentType type;

    private long userId;
}
