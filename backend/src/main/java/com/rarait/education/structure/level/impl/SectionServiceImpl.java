package com.rarait.education.structure.level.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.structure.level.LevelService;
import com.rarait.education.structure.level.SectionRepository;
import com.rarait.education.structure.level.SectionService;
import com.rarait.education.structure.level.SectionValidator;
import com.rarait.education.structure.level.resource.SectionCreateRequest;
import com.rarait.education.structure.level.resource.SectionDetailResource;
import com.rarait.education.structure.level.resource.SectionResource;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.shared.PagedRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final SectionValidator sectionValidator;
    private final LevelService levelService;
    private final InstituteLoginSession instituteLoginSession;

    public SectionServiceImpl(SectionRepository sectionRepository,
                              SectionValidator sectionValidator,
                              LevelService levelService,
                              InstituteLoginSession instituteLoginSession) {
        this.sectionRepository = sectionRepository;
        this.sectionValidator = sectionValidator;
        this.levelService = levelService;
        this.instituteLoginSession = instituteLoginSession;
    }

    @Override
    @Transactional
    public void createSection(SectionCreateRequest request) {
        sectionValidator.validate(request);
        Institute institute = instituteLoginSession.getCurrentInstitute();

        Section section = new Section();
        section.setLevel(levelService.findOneByIdAndInstitute(institute.getId(), request.getLevelId()));
        section.setName(request.getName());
        section.setStatus(Status.ACTIVE);
        sectionRepository.save(section);
    }

    @Override
    public PagedResponse<SectionResource> findAllSection(int levelId, int pageNumber) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Page<Section> resp;
        if (levelId == 0) {
            resp = sectionRepository.findAllByInstitute(institute.getId(), PagedRequest.get(pageNumber));
        } else {
            resp = sectionRepository.findAllByInstituteAndLevel(institute.getId(), levelId,
                    PagedRequest.get(pageNumber));
        }
        return new PagedResponse<>(resp.getTotalElements(), resp.getTotalPages(), pageNumber,
                SectionConvert.convertList(resp.getContent()));
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Section findOneByIdAndInstitute(int instituteId, int sectionId) {
        return sectionRepository.findOneByInstituteAndId(instituteId, sectionId);
    }

    @Override
    public SectionDetailResource findOneInfo(int sectionId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Section section = sectionRepository.findOneByInstituteAndId(institute.getId(), sectionId);
        return SectionConvert.convertDetail(section);
    }

    @Override
    @Transactional
    public List<DropdownListResource> findAllForList(int levelId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        List<Section> sections = sectionRepository.findAllByInstituteAndLevel(institute.getId(), levelId);
        return SectionConvert.convertDropdownList(sections);
    }
}
