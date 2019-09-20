package com.rarait.education.structure.subject.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.structure.level.impl.Level;
import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Entity
@Setter
@Getter
@ToString
public class Subject extends BaseEntity<Integer> {

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Level level;

    @ManyToOne(fetch = FetchType.LAZY)
    private Institute institute;

    private short theoryFullMark;
    private short theoryPassMark;

    private short practicalFullMark;
    private short practicalPassMark;

    private boolean practical = false;
    private boolean optional = false;


}