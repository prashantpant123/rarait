package com.rarait.education.vehicle.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class TransportDetailResource implements Serializable {

    @JsonProperty("number_plate")
    private String numberPlate;
    private String name;
}
