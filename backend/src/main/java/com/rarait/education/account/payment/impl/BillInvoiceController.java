package com.rarait.education.account.payment.impl;

import com.rarait.education.account.payment.BillInvoiceService;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.shared.route.InstituteRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class BillInvoiceController {

    private final BillInvoiceService billInvoiceService;

    @Autowired
    public BillInvoiceController(BillInvoiceService billInvoiceService) {
        this.billInvoiceService = billInvoiceService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = InstituteRoute.BILL)
    public void makeInvoice(){

    }
}
