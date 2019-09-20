package com.rarait.education.account.fee.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class BillNarrationCreateRequest implements Serializable {

    private String displayName;
}
