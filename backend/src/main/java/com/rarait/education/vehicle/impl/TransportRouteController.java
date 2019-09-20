package com.rarait.education.vehicle.impl;

import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.route.InstituteRoute;
import com.rarait.education.vehicle.TransportRouteService;
import com.rarait.education.vehicle.resource.TransportRouteCreateRequest;
import com.rarait.education.vehicle.resource.TransportRouteDropdownResource;
import com.rarait.education.vehicle.resource.TransportRouteResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@RestController
public class TransportRouteController {

    private final TransportRouteService transportRouteService;

    public TransportRouteController(TransportRouteService transportRouteService) {
        this.transportRouteService = transportRouteService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = InstituteRoute.TRANSPORT_ROUTE)
    public void addBusRoute(@RequestBody TransportRouteCreateRequest request) {
        transportRouteService.addBusRoute(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = InstituteRoute.TRANSPORT_ROUTE)
    public PagedResponse<TransportRouteResource> getAllRoutes(@RequestParam("page")Integer pageNumber) {
        return transportRouteService.findAllRoute(pageNumber);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = InstituteRoute.TRANSPORT_ROUTE_ID)
    public TransportRouteResource getTransportRouteDetail(@PathVariable("transport_route_id") Integer routeId) {
        return transportRouteService.findRouteDetailById(routeId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = InstituteRoute.TRANSPORT_ROUTE_LIST)
    public List<TransportRouteDropdownResource> getAllRouteForDropdown() {
        return transportRouteService.findAllRouteForDropdown();
    }
}
