package com.rarait.education.vehicle.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.vehicle.TransportRepository;
import com.rarait.education.vehicle.TransportRouteRepository;
import com.rarait.education.vehicle.TransportService;
import com.rarait.education.vehicle.TransportValidator;
import com.rarait.education.vehicle.resource.*;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;
    private final TransportValidator transportValidator;
    private final InstituteLoginSession instituteLoginSession;

    @Autowired
    public TransportServiceImpl(TransportRepository transportRepository,
                                TransportValidator transportValidator,
                                InstituteLoginSession instituteLoginSession) {
        this.transportRepository = transportRepository;
        this.transportValidator = transportValidator;
        this.instituteLoginSession = instituteLoginSession;
    }

    @Override
    @Transactional
    public void addBus(TransportCreateRequest request) {
        transportValidator.validateBus(request);
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Transport transport = new Transport();
        transport.setName(request.getName());
        transport.setNumberPlate(request.getNumberPlate());
        transport.setCapacity(request.getCapacity());
        transport.setInstitute(institute);
        transportRepository.save(transport);
    }


    @Override
    public PagedResponse<TransportCreateRequest> findAllTransports(int pageNumber) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Page<Transport> bus = transportRepository.findAllByInstitute(institute.getId(),
                PageRequest.of(pageNumber - 1, 20));
        return new PagedResponse<>(bus.getTotalElements(),
                bus.getTotalPages(),
                pageNumber,
                TransportConvert.convertToList(bus.getContent()));
    }



    @Override
    public List<TransportDropdownResource> findAllTransportForDropdown() {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        return TransportConvert.convertDDList(transportRepository.findAllByInstitute(institute.getId()));
    }

    @Override
    @Transactional
    public TransportDetailResource findTransportDetailById(int transportId) {
        return TransportConvert.convertDetail(findOneById(transportId));
    }

    @Override
    public Transport findOneById(int transportId){
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Optional<Transport> transports = transportRepository.findOneByInstituteAndId(transportId, institute.getId());
        return transports.orElseThrow(() -> new ClientRestException("Transport not found"));

    }
}