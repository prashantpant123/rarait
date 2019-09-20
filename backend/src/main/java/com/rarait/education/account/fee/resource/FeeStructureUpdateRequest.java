package com.rarait.education.account.fee.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class FeeStructureUpdateRequest extends FeeStructureCreateRequest{

    private int id;
}
