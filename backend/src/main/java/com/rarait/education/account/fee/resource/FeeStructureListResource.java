package com.rarait.education.account.fee.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Builder
@Getter
@ToString
public class FeeStructureListResource implements Serializable {

    private int id;
    private String title;
    private double amount;
    @JsonProperty("class")
    private String level;
    private boolean taxable;
}
