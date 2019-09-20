package com.rarait.education.summary;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class SummaryConvert {

    private SummaryConvert() {
    }

    public static SummaryResource convert(Summary summary){
        return SummaryResource.builder()
                .totalStudent(summary.getTotalStudent())
                .totalTeacher(summary.getTotalTeacher())
                .build();
    }
}
