package com.rarait.education.account.payment.impl;

import com.rarait.education.account.fee.resource.BillNarrationCreateRequest;
import com.rarait.education.account.fee.resource.BillNarrationResponse;
import com.rarait.education.account.fee.resource.BillNarrationUpdateRequest;
import com.rarait.education.account.payment.BillNarrationRepository;
import com.rarait.education.account.payment.BillNarrationService;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.framework.shared.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class BillNarrationServiceImpl implements BillNarrationService {

    private final BillNarrationRepository billNarrationRepository;
    private final InstituteLoginSession instituteLoginSession;

    @Autowired
    public BillNarrationServiceImpl(BillNarrationRepository billNarrationRepository,
                                    InstituteLoginSession instituteLoginSession) {
        this.billNarrationRepository = billNarrationRepository;
        this.instituteLoginSession = instituteLoginSession;
    }

    @Override
    public void addBillNarration(BillNarrationCreateRequest request) {
        BillNarration narration = new BillNarration();
        narration.setDisplay(request.getDisplayName());
        narration.setInstitute(instituteLoginSession.getCurrentInstitute());
        narration.setStatus(Status.ACTIVE);
        billNarrationRepository.save(narration);
    }

    @Override
    public void updateBillNarration(BillNarrationUpdateRequest request) {
        BillNarration narration = billNarrationRepository.findOneByInstituteAndId(instituteLoginSession.getCurrentInstitute().getId(),
                request.getId());
        narration.setDisplay(request.getDisplayName());
        billNarrationRepository.save(narration);
    }

    @Override
    public List<BillNarrationResponse> getAllNarrationForInstitute() {
        return BillInvoiceConvert.convertToNarrationList(billNarrationRepository.findAllNarrationByInstitute(
                instituteLoginSession.getCurrentInstitute().getId(), Status.ACTIVE));
    }
}
