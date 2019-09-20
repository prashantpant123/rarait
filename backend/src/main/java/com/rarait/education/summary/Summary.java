package com.rarait.education.summary;

import com.rarait.education.institute.impl.Institute;
import com.rarait.framework.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Entity
@Getter
@Setter
@ToString
public class Summary extends BaseEntity<Short> {

    private int totalStudent;
    private int totalTeacher;

    @OneToOne
    private Institute institute;

}
