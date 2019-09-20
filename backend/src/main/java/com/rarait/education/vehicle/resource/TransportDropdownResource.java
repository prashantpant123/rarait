package com.rarait.education.vehicle.resource;

import lombok.*;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@ToString
public class TransportDropdownResource implements Serializable {

    private int id;
    private String name;

    public TransportDropdownResource(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
