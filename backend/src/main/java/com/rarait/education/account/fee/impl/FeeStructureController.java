package com.rarait.education.account.fee.impl;

import com.rarait.education.account.fee.FeeStructureService;
import com.rarait.education.account.fee.resource.FeeStructureCreateRequest;
import com.rarait.education.account.fee.resource.FeeStructureDetailResource;
import com.rarait.education.account.fee.resource.FeeStructureListResource;
import com.rarait.education.account.fee.resource.FeeStructureUpdateRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.route.InstituteRoute;
import com.rarait.framework.exception.ClientRestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class FeeStructureController {

    private final FeeStructureService feeStructureService;

    @Autowired
    public FeeStructureController(FeeStructureService feeStructureService) {
        this.feeStructureService = feeStructureService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = InstituteRoute.FEE)
    public void createFee(@RequestBody FeeStructureCreateRequest request) {
        feeStructureService.addFeeStructure(request);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = InstituteRoute.FEE_UPDATE)
    public void updateFee(@RequestBody FeeStructureUpdateRequest request) {
        feeStructureService.updateFeeStructure(request);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = InstituteRoute.FEE)
    public PagedResponse<FeeStructureListResource> getFeeStructureList(@RequestParam("page") int pageNumber) {
        return feeStructureService.findAllFeeStructure(pageNumber);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = InstituteRoute.FEE_ID)
    public FeeStructureDetailResource getFeeStructureDetail(@PathVariable("fee_id") int feeStructureId) {
        return feeStructureService.getFeeStructureDetail(feeStructureId);
    }
}
