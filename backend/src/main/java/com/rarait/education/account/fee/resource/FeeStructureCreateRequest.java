package com.rarait.education.account.fee.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class FeeStructureCreateRequest implements Serializable {

    @JsonProperty("class_id")
    private int level;
    private double amount;
    private String title;

    private boolean discount;

    @JsonProperty("discount_value")
    private short discountValue;

    private boolean taxable;

    @JsonProperty("tax_value")
    private short taxValue;

    private String description;
}