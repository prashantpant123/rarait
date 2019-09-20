package com.rarait.education.utility.occupation.impl;

import com.rarait.education.utility.occupation.spi.OccupationRepository;
import com.rarait.framework.shared.Status;
import com.rarait.education.utility.occupation.spi.OccupationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since Aug 28, 2018
 */
@Slf4j
@Service
public class OccupationServiceImpl implements OccupationService {

    private final OccupationRepository occupationRepository;

    @Autowired
    public OccupationServiceImpl(OccupationRepository occupationRepository) {
        this.occupationRepository = occupationRepository;
    }

    @Override
    public Occupation findOneById(Short occupationId){
        return occupationRepository.findOneById(occupationId, Status.ACTIVE);
    }

    @Override
    public void addOccupation(OccupationCreateRequest request){
        Occupation occupation= new Occupation();
        occupation.setName(request.getDisplayName());
        occupation.setStatus(Status.ACTIVE);
        occupationRepository.save(occupation);
    }

    @Override
    @Transactional
    public void updateOccupation(OccupationUpdateRequest request){
        Occupation oldOccupation= findOneById(request.getId());
        oldOccupation.setStatus(Status.INACTIVE);
        occupationRepository.save(oldOccupation);

        Occupation occupation = new Occupation();
        occupation.setName(request.getDisplayName());
        occupation.setStatus(Status.ACTIVE);
        occupationRepository.save(occupation);
    }

    @Override
    public List<OccupationResource> findAll() {
        List<Occupation> occupationList = occupationRepository.findAllByStatus(Status.ACTIVE);
        List<OccupationResource> resources = new ArrayList<>();
        occupationList.forEach(o ->
                resources.add(OccupationResource.builder()
                        .id(o.getId())
                        .name(o.getName())
                        .build()));
        resources.sort(Comparator.comparing(OccupationResource::getName));
        return resources;
    }
}