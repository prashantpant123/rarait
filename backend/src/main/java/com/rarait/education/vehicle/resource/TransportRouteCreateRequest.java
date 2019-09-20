package com.rarait.education.vehicle.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class TransportRouteCreateRequest implements Serializable{

    @JsonProperty("transport_id")
    private Integer transportId;

    @JsonProperty("route_path")
    private String route;

    @JsonProperty("pickup_time")
    private String pickupTime;

    @JsonProperty("drop_time")
    private String dropTime;
}
