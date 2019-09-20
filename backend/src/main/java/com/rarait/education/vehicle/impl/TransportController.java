package com.rarait.education.vehicle.impl;

import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.route.InstituteRoute;
import com.rarait.education.vehicle.resource.*;
import com.rarait.education.vehicle.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class TransportController {

    private final TransportService transportService;

    @Autowired
    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = InstituteRoute.TRANSPORT)
    public PagedResponse<TransportCreateRequest> getAllTransport(@RequestParam("page") int pageNumber) {
        return transportService.findAllTransports(pageNumber);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = InstituteRoute.TRANSPORT)
    public void addBus(@RequestBody TransportCreateRequest request) {
        transportService.addBus(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = InstituteRoute.TRANSPORT_ID)
    public TransportDetailResource getTransportDetail(@PathVariable("transport_id") Integer transportId) {
        return transportService.findTransportDetailById(transportId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = InstituteRoute.TRANSPORT_LIST)
    public List<TransportDropdownResource> getAllTransportForDropdown() {
        return transportService.findAllTransportForDropdown();
    }

}
