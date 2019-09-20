package com.rarait.education.account.fee.impl;

import com.rarait.education.account.fee.FeeStructureRepository;
import com.rarait.education.account.fee.FeeStructureService;
import com.rarait.education.account.fee.FeeStructureValidator;
import com.rarait.education.account.fee.resource.FeeStructureCreateRequest;
import com.rarait.education.account.fee.resource.FeeStructureDetailResource;
import com.rarait.education.account.fee.resource.FeeStructureListResource;
import com.rarait.education.account.fee.resource.FeeStructureUpdateRequest;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.structure.level.LevelService;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.shared.PagedRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class FeeStructureServiceImpl implements FeeStructureService {

    private final FeeStructureRepository feeStructureRepository;
    private final LevelService levelService;
    private final FeeStructureValidator feeStructureValidator;
    private final InstituteLoginSession instituteLoginSession;

    @Autowired
    public FeeStructureServiceImpl(FeeStructureRepository feeStructureRepository,
                                   LevelService levelService,
                                   InstituteLoginSession instituteLoginSession,
                                   FeeStructureValidator feeStructureValidator) {
        this.feeStructureRepository = feeStructureRepository;
        this.levelService = levelService;
        this.instituteLoginSession = instituteLoginSession;
        this.feeStructureValidator = feeStructureValidator;
    }

    @Override
    @Transactional
    public void addFeeStructure(FeeStructureCreateRequest request) {
        feeStructureValidator.validate(request);
        Institute institute = instituteLoginSession.getCurrentInstitute();

        FeeStructure structure = new FeeStructure();
        structure.setLevel(levelService.getLevelById(request.getLevel()));
        structure.setTotalAmount(request.getAmount());
        structure.setStatus(Status.ACTIVE);
        structure.setTitle(request.getTitle());
        structure.setDescription(request.getDescription());
        structure.setInstitute(institute);
        structure.setTaxable(request.isTaxable());
        structure.setTaxValue(request.getTaxValue());
        structure.setDiscountable(request.isDiscount());
        structure.setDiscountValue(request.getDiscountValue());
        feeStructureRepository.save(structure);
    }

    @Override
    public void updateFeeStructure(FeeStructureUpdateRequest request) {
        feeStructureValidator.validate(request);

        FeeStructure fee = findOneById(request.getId());
        fee.setDescription(request.getDescription());
        fee.setTotalAmount(request.getAmount());
        fee.setTaxable(request.isTaxable());
        fee.setTaxValue(request.getTaxValue());
        fee.setDiscountable(request.isDiscount());
        fee.setDiscountValue(request.getDiscountValue());
        feeStructureRepository.save(fee);
    }

    @Override
    @Transactional
    public PagedResponse<FeeStructureListResource> findAllFeeStructure(int pageNumber) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Page<FeeStructure> feeStructures = feeStructureRepository.findAllByInstitute(institute.getId(),
                PagedRequest.get(pageNumber, Sort.by("title")));
        return new PagedResponse(feeStructures.getTotalElements(),
                feeStructures.getTotalPages(),
                pageNumber,
                FeeConvert.convertToList(feeStructures.getContent()));
    }

    @Override
    @Transactional
    public FeeStructureDetailResource getFeeStructureDetail(int feeStructureId) {
        FeeStructure fee = findOneById(feeStructureId);
        return FeeConvert.convertDetail(fee);
    }

    private FeeStructure findOneById(int feeStructureId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Optional<FeeStructure> fs = feeStructureRepository.findByIdAndInstitute(feeStructureId, institute.getId());
        return fs.orElseThrow(() -> new ClientRestException("Fee structure not found"));
    }
}