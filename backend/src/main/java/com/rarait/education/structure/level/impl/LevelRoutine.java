package com.rarait.education.structure.level.impl;

import com.rarait.education.structure.subject.impl.Subject;
import com.rarait.framework.domain.BaseEntity;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@Entity
@ToString
public class LevelRoutine extends BaseEntity<Long> {

    @ManyToOne
    private Subject subject;

    @Column(nullable = false)
    private Time periodStartTime;

    @Column(nullable = false)
    private Time periodEndTime;

    @Column(nullable = false)
    private short dateOfWeek;

    private Status status;
}
