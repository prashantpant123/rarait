package com.rarait.education.utility.nationality;

import com.rarait.education.shared.resource.DropdownListResource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class NationalityConvert {

    private NationalityConvert() {
    }

    public static DropdownListResource convert(Nationality nationality) {
        return DropdownListResource.builder()
                .name(nationality.getName())
                .id(nationality.getId())
                .build();
    }

    public static List<DropdownListResource> convertList(List<Nationality> nationalities) {
        return nationalities.stream()
                .sorted(Comparator.comparing(Nationality::getName))
                .map(NationalityConvert::convert)
                .collect(Collectors.toList());
    }
}
