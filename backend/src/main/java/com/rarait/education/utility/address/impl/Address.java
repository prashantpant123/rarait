package com.rarait.education.utility.address.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullAddress;

    private String name;

    private GeoRegionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Address parentId;

    private Date createdDate;
}