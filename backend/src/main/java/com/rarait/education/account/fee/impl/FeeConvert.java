package com.rarait.education.account.fee.impl;

import com.rarait.education.account.fee.resource.FeeStructureDetailResource;
import com.rarait.education.account.fee.resource.FeeStructureListResource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class FeeConvert {
    private FeeConvert() {
    }

    public static FeeStructureListResource convert(FeeStructure structure) {
        return FeeStructureListResource.builder()
                .id(structure.getId())
                .level(structure.getLevel() == null ? "General" : structure.getLevel().getName())
                .title(structure.getTitle())
                .amount(structure.getTotalAmount())
                .taxable(structure.getTaxValue() > 0 ? true : false)
                .build();
    }

    public static List<FeeStructureListResource> convertToList(List<FeeStructure> structures) {
        return structures.stream()
                .map(FeeConvert::convert)
                .collect(Collectors.toList());
    }

    public static FeeStructureDetailResource convertDetail(FeeStructure fee) {
        return FeeStructureDetailResource.builder()
                .id(fee.getId())
                .title(fee.getTitle())
                .description(fee.getDescription())
                .amount(fee.getTotalAmount())
                .taxable(fee.isTaxable())
                .taxValue(fee.getTaxValue() > 0 ? fee.getTaxValue() : 0)
                .discount(fee.isDiscountable())
                .discountValue(fee.getDiscountValue())
                .createDate(fee.getCreatedDate())
                .level(fee.getLevel() == null ? "General" : fee.getLevel().getName())
                .build();
    }
}
