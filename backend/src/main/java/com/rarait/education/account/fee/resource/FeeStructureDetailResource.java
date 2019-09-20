package com.rarait.education.account.fee.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Builder
@Getter
@ToString
public class FeeStructureDetailResource implements Serializable {
    private int id;
    private String title;
    private String description;
    private double amount;

    private boolean discount;

    @JsonProperty("discount_value")
    private short discountValue;

    private boolean taxable;

    @JsonProperty("tax_value")
    private short taxValue;

    @JsonProperty("created_date")
    private Date createDate;

    @JsonProperty("last_modified_date")
    private Date lastModifiedDate;

    @JsonProperty("class")
    private String level;
}
