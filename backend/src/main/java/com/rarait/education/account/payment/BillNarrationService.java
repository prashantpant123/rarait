package com.rarait.education.account.payment;

import com.rarait.education.account.fee.resource.BillNarrationCreateRequest;
import com.rarait.education.account.fee.resource.BillNarrationResponse;
import com.rarait.education.account.fee.resource.BillNarrationUpdateRequest;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface BillNarrationService {

    void addBillNarration(BillNarrationCreateRequest request);

    void updateBillNarration(BillNarrationUpdateRequest request);

    List<BillNarrationResponse> getAllNarrationForInstitute();
}
