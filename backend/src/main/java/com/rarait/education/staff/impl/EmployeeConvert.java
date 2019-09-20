package com.rarait.education.staff.impl;

import com.rarait.education.document.Document;
import com.rarait.education.shared.route.InstituteRoute;
import com.rarait.education.staff.resource.EmployeeDetailResource;
import com.rarait.education.staff.resource.EmployeeListResponse;
import com.rarait.education.staff.resource.EmployeeTypeDropdownResource;
import com.rarait.education.utility.BaseConversion;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class EmployeeConvert {

    private EmployeeConvert() {
    }

    public static EmployeeTypeDropdownResource convertType(EmployeeType type) {
        return EmployeeTypeDropdownResource.builder()
                .id(type.getValue())
                .name(type.name())
                .build();
    }

    public static List<EmployeeTypeDropdownResource> convertTypeList() {
        return new ArrayList<>(EnumSet.allOf(EmployeeType.class))
                .stream()
                .map(EmployeeConvert::convertType)
                .collect(Collectors.toList());
    }

    public static EmployeeListResponse convert(Employee employee) {
        return EmployeeListResponse.builder()
                .id(employee.getId())
                .fullName(employee.getFirstName() + " " + employee.getLastName())
                .type(employee.getType().name())
                .primaryContact(employee.getPrimaryContact())
                .currentAddress(employee.getCurrentAddress())
//                + employee.getCurrentAddress() == null ? "" : ", " + employee.getCurrentAddress().getFullAddress())
                .build();
    }

    public static List<EmployeeListResponse> convertToList(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeConvert::convert)
                .collect(Collectors.toList());
    }

    public static EmployeeDetailResource convertDetail(Employee employee, Document document, String domain) {
        EmployeeDetailResource res = new EmployeeDetailResource();
        res.setId(employee.getId());
        res.setFirstName(employee.getFirstName());
        res.setLastName(employee.getLastName());
        res.setDateOfBirth(employee.getDobAd());
        res.setDobBs(employee.getDobBs());
        res.setJoiningDate(employee.getJoiningDate());
        res.setEmployeeType(employee.getType().toString());
        res.setGender(employee.getGender().toString());
        res.setPrimaryContact(employee.getPrimaryContact());
        res.setSecondaryContact(employee.getSecondaryContact());
        res.setPermanentAddress(employee.getPermanentAddress());
        res.setCurrentAddress(employee.getCurrentAddress());
        res.setQualification(employee.getQualification());
        res.setDesignation(employee.getDesignation());
        res.setPicture(document == null ? "" : domain + InstituteRoute.INSTITUTE + "/" + BaseConversion.getCode(employee.getInstitute().getId()) +
                "/file/" + BaseConversion.getCode(employee.getId()) + "/" + document.getFilename());
        res.setBloodGroup(employee.getBloodGroup().getValue());
        res.setEmployeeId(employee.getEmployeeId());
        res.setExperienceSummary(employee.getExperienceSummary());
        return res;
    }
}

