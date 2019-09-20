package com.rarait.education.vehicle.resource;

import lombok.*;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */

@Getter
@ToString(callSuper = true)
public class TransportRouteDropdownResource extends TransportDropdownResource implements Serializable {

    public TransportRouteDropdownResource(int id, String name) {
        super(id, name);
    }
}
