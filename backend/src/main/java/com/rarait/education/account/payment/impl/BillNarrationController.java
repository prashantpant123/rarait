package com.rarait.education.account.payment.impl;

import com.rarait.education.account.fee.resource.BillNarrationCreateRequest;
import com.rarait.education.account.fee.resource.BillNarrationResponse;
import com.rarait.education.account.fee.resource.BillNarrationUpdateRequest;
import com.rarait.education.account.payment.BillNarrationService;
import com.rarait.education.shared.route.InstituteRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class BillNarrationController {

    private final BillNarrationService billNarrationService;

    @Autowired
    public BillNarrationController(BillNarrationService billNarrationService) {
        this.billNarrationService = billNarrationService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = InstituteRoute.BILL_NARRATION)
    public void addBillNarration(@RequestBody BillNarrationCreateRequest request) {
        billNarrationService.addBillNarration(request);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = InstituteRoute.BILL_NARRATION_UPDATE)
    public void updateBillNarration(@RequestBody BillNarrationUpdateRequest request) {
        billNarrationService.updateBillNarration(request);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = InstituteRoute.BILL_NARRATION)
    public @ResponseBody
    List<BillNarrationResponse> getAllNarration() {
        return billNarrationService.getAllNarrationForInstitute();
    }
}