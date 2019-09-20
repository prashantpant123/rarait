package com.rarait.education.account.payment.impl;

import com.rarait.education.account.fee.resource.BillNarrationResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class BillInvoiceConvert {

    private BillInvoiceConvert() {
    }

    public static BillNarrationResponse convertToNarration(BillNarration bill) {
        return BillNarrationResponse.builder()
                .display(bill.getDisplay())
                .id(bill.getId())
                .build();
    }

    public static List<BillNarrationResponse> convertToNarrationList(List<BillNarration> bills) {
        return bills.stream()
                .map(BillInvoiceConvert::convertToNarration)
                .collect(Collectors.toList());
    }
}
