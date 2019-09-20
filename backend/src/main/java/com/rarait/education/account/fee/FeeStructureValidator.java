package com.rarait.education.account.fee;

import com.rarait.education.account.fee.resource.FeeStructureCreateRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface FeeStructureValidator {
    void validate(FeeStructureCreateRequest request);
}
