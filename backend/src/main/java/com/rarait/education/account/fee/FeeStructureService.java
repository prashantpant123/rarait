package com.rarait.education.account.fee;

import com.rarait.education.account.fee.resource.FeeStructureCreateRequest;
import com.rarait.education.account.fee.resource.FeeStructureDetailResource;
import com.rarait.education.account.fee.resource.FeeStructureListResource;
import com.rarait.education.account.fee.resource.FeeStructureUpdateRequest;
import com.rarait.education.shared.PagedResponse;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface FeeStructureService {

    void addFeeStructure(FeeStructureCreateRequest request);

    void updateFeeStructure(FeeStructureUpdateRequest request);

    PagedResponse<FeeStructureListResource> findAllFeeStructure(int pageNumber);

    FeeStructureDetailResource getFeeStructureDetail(int feeStructureId);
}