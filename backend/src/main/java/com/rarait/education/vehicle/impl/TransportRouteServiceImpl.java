package com.rarait.education.vehicle.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.vehicle.TransportRouteRepository;
import com.rarait.education.vehicle.TransportRouteService;
import com.rarait.education.vehicle.TransportRouteValidator;
import com.rarait.education.vehicle.TransportService;
import com.rarait.education.vehicle.resource.TransportRouteCreateRequest;
import com.rarait.education.vehicle.resource.TransportRouteDropdownResource;
import com.rarait.education.vehicle.resource.TransportRouteResource;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class TransportRouteServiceImpl implements TransportRouteService {

    private final TransportRouteRepository transportRouteRepository;
    private final InstituteLoginSession instituteLoginSession;
    private final TransportRouteValidator transportRouteValidator;
    private final TransportService transportService;

    public TransportRouteServiceImpl(TransportRouteRepository transportRouteRepository,
                                     InstituteLoginSession instituteLoginSession,
                                     TransportRouteValidator transportRouteValidator,
                                     TransportService transportService) {
        this.transportRouteRepository = transportRouteRepository;
        this.instituteLoginSession = instituteLoginSession;
        this.transportRouteValidator = transportRouteValidator;
        this.transportService = transportService;
    }

    @Override
    @Transactional
    public void addBusRoute(TransportRouteCreateRequest request) {
        transportRouteValidator.validateBusRoute(request);

        Transport transport = transportService.findOneById(request.getTransportId());
        TransportRoute route = new TransportRoute();
        route.setPickupTime(request.getPickupTime());
        route.setDropTime(request.getDropTime());
        route.setRoute(request.getRoute());
        route.setTransport(transport);
        transportRouteRepository.save(route);
    }

    @Override
    public PagedResponse<TransportRouteResource> findAllRoute(int pageNumber) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Page<TransportRoute> routes = transportRouteRepository.findAllByStatusAndInstitute(Status.ACTIVE, institute.getId(),
                PageRequest.of(pageNumber - 1, 20, Sort.by("route")));
        return new PagedResponse<>(routes.getTotalElements(),
                routes.getTotalPages(),
                pageNumber,
                TransportConvert.covertAllRoutes(routes.getContent()));
    }

    @Override
    public TransportRoute findOneByIdAndInstitute(int instituteId, int transportId) {
        Optional<TransportRoute> busRoute = transportRouteRepository.findOneByIdAndInstitute(instituteId, transportId);
        return busRoute.orElseThrow(() -> new ClientRestException("No transport route"));
    }

    @Override
    public TransportRoute findOneById(int transportId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Optional<TransportRoute> busRoute = transportRouteRepository.findOneByIdAndInstitute(institute.getId(), transportId);
        return busRoute.orElseThrow(() -> new ClientRestException("No transport route"));
    }

    @Override
    public List<TransportRouteDropdownResource> findAllRouteForDropdown() {
        return TransportConvert.convertDDRouteList(transportRouteRepository.findAllByStatus(Status.ACTIVE));
    }

    @Override
    @Transactional
    public TransportRouteResource findRouteDetailById(int transportRouteId) {
        return TransportConvert.convertRoute(findOneById(transportRouteId));
    }
}
