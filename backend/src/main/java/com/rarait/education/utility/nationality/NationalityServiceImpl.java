package com.rarait.education.utility.nationality;

import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
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
public class NationalityServiceImpl implements NationalityService {

    private final NationalityRepository nationalityRepository;

    public NationalityServiceImpl(NationalityRepository nationalityRepository) {
        this.nationalityRepository = nationalityRepository;
    }

    @Override
    @Transactional
    public void addNationality(String name) {
        Nationality nationality = new Nationality();
        nationality.setName(name);
        nationality.setStatus(Status.ACTIVE);
        nationalityRepository.save(nationality);
    }

    @Override
    public Nationality findOneById(short nationalityId) {
        Optional<Nationality> nationality = nationalityRepository.findOneByIdAndStatus(Status.ACTIVE, nationalityId);
        return nationality.orElseThrow(() -> new ClientRestException("Nationality not found"));
    }

    @Override
    public List<DropdownListResource> findAllNationality() {
        List<Nationality> nationalities = nationalityRepository.findAllActive(Status.ACTIVE);
        return NationalityConvert.convertList(nationalities);
    }
}
