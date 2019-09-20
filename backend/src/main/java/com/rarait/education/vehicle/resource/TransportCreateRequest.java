package com.rarait.education.vehicle.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Builder
@ToString
public class TransportCreateRequest implements Serializable {

    @JsonProperty("number_plate")
    private String numberPlate;

    private String name;

    private Integer id;
    private Short capacity;

}