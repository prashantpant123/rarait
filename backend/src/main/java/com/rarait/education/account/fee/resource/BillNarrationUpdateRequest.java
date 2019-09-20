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
public class BillNarrationUpdateRequest extends BillNarrationCreateRequest{

    private int id;
}
