package com.rarait.education.utility.address.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class AddressCreateRequest implements Serializable {

    private String name;

    @JsonProperty("full_address")
    private String fullAddress;
    private GeoRegionType type;

    public AddressCreateRequest(String name, String fullAddress, GeoRegionType type) {
        this.name = name;
        this.fullAddress = fullAddress;
        this.type = type;
    }
}
