package com.rarait.education.account.payment.impl;

import com.rarait.education.account.payment.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class BillInvoiceServiceImpl implements BillInvoiceService {

    private final BillInvoiceRepository billInvoiceRepository;
    private final BillInvoiceParticularRepository billInvoiceParticularRepository;
    private final BillNarrationService billNarrationService;

    @Autowired
    public BillInvoiceServiceImpl(BillInvoiceRepository billInvoiceRepository,
                                  BillInvoiceParticularRepository billInvoiceParticularRepository,
                                  BillNarrationService billNarrationService) {
        this.billInvoiceRepository = billInvoiceRepository;
        this.billInvoiceParticularRepository = billInvoiceParticularRepository;
        this.billNarrationService = billNarrationService;
    }

}