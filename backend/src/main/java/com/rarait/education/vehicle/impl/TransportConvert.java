package com.rarait.education.vehicle.impl;

import com.rarait.education.vehicle.resource.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class TransportConvert {

    private TransportConvert() {
    }

    public static List<TransportRouteResource> covertAllRoutes(List<TransportRoute> routes) {
        return routes.stream()
                .map(TransportConvert::convertRoute)
                .collect(Collectors.toList());
    }

    public static TransportRouteResource convertRoute(TransportRoute route) {
        return TransportRouteResource.builder()
                .routePath(route.getRoute())
                .pickupTime(route.getPickupTime())
                .dropTime(route.getDropTime())
                .numberPlate(route.getTransport().getNumberPlate())
                .id(route.getId())
                .build();
    }

    public static TransportCreateRequest convert(Transport transport) {
        return TransportCreateRequest.builder()
                .numberPlate(transport.getNumberPlate())
                .capacity(transport.getCapacity())
                .name(transport.getName())
                .id(transport.getId())
                .build();
    }

    public static List<TransportCreateRequest> convertToList(List<Transport> transports) {
        return transports.stream()
                .map(TransportConvert::convert)
                .collect(Collectors.toList());
    }

    public static TransportRouteDropdownResource convertDDRoute(TransportRoute route) {
        return new TransportRouteDropdownResource(route.getId(), route.getRoute());
    }

    public static List<TransportRouteDropdownResource> convertDDRouteList(List<TransportRoute> routes) {
        return routes.stream()
                .map(TransportConvert::convertDDRoute)
                .collect(Collectors.toList());
    }

    public static TransportDropdownResource convertDD(Transport route) {
        return new TransportRouteDropdownResource(route.getId(), route.getNumberPlate());
    }

    public static List<TransportDropdownResource> convertDDList(List<Transport> routes) {
        return routes.stream()
                .map(TransportConvert::convertDD)
                .collect(Collectors.toList());
    }

    public static TransportDetailResource convertDetail(Transport transport) {
        return TransportDetailResource.builder()
                .name(transport.getName())
                .numberPlate(transport.getNumberPlate())
                .build();
    }
}
