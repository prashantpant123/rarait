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
@Getter
@Builder
@ToString
public class TransportRouteResource implements Serializable{

    private Integer id;

    @JsonProperty("route_path")
    private String routePath;

    @JsonProperty("number_plate")
    private String numberPlate;

    @JsonProperty("pickup_time")
    private String pickupTime;

    @JsonProperty("drop_time")
    private String dropTime;
}
