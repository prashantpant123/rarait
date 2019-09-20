package com.rarait.education.academic.impl;

import com.rarait.education.academic.resource.AcademicSessionDropdownResource;
import com.rarait.education.academic.resource.AcademicSessionResource;
import com.rarait.framework.shared.DateUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public abstract class AcademicSessionConvert {

    private AcademicSessionConvert() {
    }

    public static AcademicSessionResource convert(AcademicSession request) {
        AcademicSessionResource response = new AcademicSessionResource();
        response.setId(request.getId());
        response.setName(request.getName());
        response.setCurrentSession(request.isCurrentSession());
        response.setStartDateAD(request.getStartDateAd());
        response.setEndDateAD(request.getEndDateAd());
        response.setStartDateBS(request.getStartDateBs());
        response.setEndDateBS(request.getEndDateBs());
        return response;
    }

    public static List<AcademicSessionResource> convertList(List<AcademicSession> request) {
        return request.stream()
                .map(AcademicSessionConvert::convert)
                .collect(Collectors.toList());
    }

    public static AcademicSessionDropdownResource convertDD(AcademicSession session) {
        return AcademicSessionDropdownResource.builder()
                .id(session.getId())
                .name(session.getName())
                .build();
    }

    public static List<AcademicSessionDropdownResource> convertDDList(List<AcademicSession> sessions) {
        return sessions.stream()
                .map(AcademicSessionConvert::convertDD)
                .collect(Collectors.toList());
    }
}
