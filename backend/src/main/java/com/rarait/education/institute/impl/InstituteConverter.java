package com.rarait.education.institute.impl;

import com.rarait.education.institute.resource.*;
import com.rarait.education.shared.route.AdminRoute;
import com.rarait.education.utility.BaseConversion;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class InstituteConverter {

    private InstituteConverter() {
    }

    public static InstituteBasicInfo convertBasicInfo(Institute institute) {
        return InstituteBasicInfo.builder()
                .name(institute.getName())
                .build();
    }

    public static InstituteResponse convert(Institute institute) {
        return InstituteResponse.builder()
                .name(institute.getName())
                .address(institute.getAddress())
                .id(institute.getId())
                .build();
    }

    public static List<InstituteResponse> convertToList(List<Institute> institutes) {
        return institutes.stream()
                .map(InstituteConverter::convert)
                .collect(Collectors.toList());
    }

    public static InstituteDetailResource convertDetail(Institute institute,
                                                        List<InstituteLoginResource> ilr,
                                                        List<ContactPersonResource> cpr, String domain) {
        return InstituteDetailResource.builder()
                .name(institute.getName())
                .address(institute.getAddress())
                .principal(institute.getPrincipal())
                .landline(institute.getLandline())
                .website(institute.getWebsite())
                .registrationPrefix(institute.getRegistrationPrefix())
                .logoPath(domain + AdminRoute.INSTITUTE + "/" + BaseConversion.getCode(institute.getId()) + "/file/" + institute.getFilename())
                .user(ilr.isEmpty() ? "" : ilr.get(0).getUsername())
                .contact(cpr.isEmpty() ? null : cpr.get(0))
                .build();
    }

    public static InstituteLoginResource convertLogin(InstituteLogin login) {
        return InstituteLoginResource.builder()
                .id(login.getId())
                .username(login.getUser().getUsername())
                .status(login.getStatus().toString())
                .build();
    }

    public static List<InstituteLoginResource> convertLoginList(List<InstituteLogin> instituteLogins) {
        return instituteLogins.stream()
                .map(InstituteConverter::convertLogin)
                .collect(Collectors.toList());
    }

    public static ContactPersonResource convertContactPerson(ContactPerson contactPerson) {
        return ContactPersonResource.builder()
                .id(contactPerson.getId())
                .fullname(contactPerson.getFullname())
                .designation(contactPerson.getDesignation())
                .mobileNumber(contactPerson.getMobileNumber())
                .build();
    }

    public static List<ContactPersonResource> convertContactPersonList(List<ContactPerson> contactPeople) {
        return contactPeople.stream()
                .map(InstituteConverter::convertContactPerson)
                .collect(Collectors.toList());
    }
}
