package com.rarait.education.utility.address.impl;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class AddressConverter {

    private AddressConverter() {
    }

    public static AddressResource convert(Address address) {
        return AddressResource.builder()
                .id(address.getId())
                .name(address.getName())
                .build();
    }

    public static List<AddressResource> convertToList(List<Address> address) {
        return address.stream()
                .map(AddressConverter::convert)
                .collect(Collectors.toList());
    }
}
