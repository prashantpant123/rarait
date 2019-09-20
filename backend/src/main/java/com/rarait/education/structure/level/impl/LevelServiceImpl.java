package com.rarait.education.structure.level.impl;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.structure.department.DepartmentService;
import com.rarait.education.structure.department.impl.DepartmentType;
import com.rarait.education.structure.level.LevelRepository;
import com.rarait.education.structure.level.LevelService;
import com.rarait.education.structure.level.LevelValidator;
import com.rarait.education.structure.level.resource.LevelCreateRequest;
import com.rarait.education.structure.level.resource.LevelResource;
import com.rarait.education.structure.level.resource.LevelUpdateRequest;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.shared.PagedRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.InputUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class LevelServiceImpl implements LevelService {

    private LevelRepository levelRepository;
    private LevelValidator levelValidator;
    private DepartmentService departmentService;
    private final InstituteLoginSession instituteLoginSession;

    @Autowired
    public LevelServiceImpl(
            LevelRepository levelRepository,
            LevelValidator levelValidator,
            DepartmentService departmentService,
            InstituteLoginSession instituteLoginSession) {
        this.levelRepository = levelRepository;
        this.levelValidator = levelValidator;
        this.departmentService = departmentService;
        this.instituteLoginSession = instituteLoginSession;
    }

    @Override
    public void addLevel(LevelCreateRequest request) {
        levelValidator.validate(request);

        Institute institute = instituteLoginSession.getCurrentInstitute();
        Level level = new Level();
        level.setName(request.getName());
        level.setCode(request.getCode());
        level.setInstitute(institute);
        level.setDepartment(DepartmentType.getEnum(request.getDepartment()));
        levelRepository.save(level);
    }

    @Override
    @Transactional
    public void updateLevel(LevelUpdateRequest request) {
        levelValidator.validate(request);

        Level level = getLevelById(request.getId());
        level.setName(request.getName());
        level.setCode(request.getCode());
        levelRepository.save(level);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Level getLevelById(int levelId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        return levelRepository.findByIdAndInstituteId(institute.getId(), levelId);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Level findOneByIdAndInstitute(int instituteId, int levelId) {
        Optional<Level> levels = Optional.ofNullable(levelRepository.findByIdAndInstituteId(instituteId, levelId));
        return levels.orElseThrow(() -> new ClientRestException("Request failed: class not found"));
    }

    @Override
    @Transactional
    public LevelResource getLevelFromId(int levelId) {
        return LevelConvert.convert(getLevelById(levelId));
    }

    @Override
    @Transactional
    public PagedResponse<LevelResource> findAllForInstitute(int pageNumber, String sortField,
                                                            Boolean ascend) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Page<Level> levels = levelRepository.findAll((Root<Level> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                    Path<Long> path = root.get("institute").get("id");
                    return cb.equal(path, institute.getId());
                }, InputUtil.isEmpty(sortField) || InputUtil.isEmpty(ascend) ? PagedRequest.get(pageNumber, Sort.by("id").descending()) :
                        PagedRequest.get(pageNumber, ascend ? Sort.by("name").ascending() : Sort.by("name").descending())
        );

        return new PagedResponse<>(levels.getTotalElements(),
                levels.getTotalPages(),
                pageNumber,
                LevelConvert.convertList(levels.getContent()));
    }

    @Override
    @Transactional
    public List<DropdownListResource> findAllForDropdown() {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        List<Level> levels = levelRepository.findForDropdownByInstitute(institute.getId());
        return LevelConvert.convertDropdownToList(levels);
    }

    @Override
    public List<DropdownListResource> findAllDepartment() {
        return departmentService.getAllForDropdown();
    }
}