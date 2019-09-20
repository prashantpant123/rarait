package com.rarait.education.account.fee.resource;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Builder
@ToString
public class BillNarrationResponse implements Serializable {

    private int id;
    private String display;
}