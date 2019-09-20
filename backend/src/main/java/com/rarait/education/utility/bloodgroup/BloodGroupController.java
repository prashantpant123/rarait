package com.rarait.education.utility.bloodgroup;

import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.education.shared.route.UtilityRoute;
import com.rarait.education.utility.nationality.Nationality;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@RestController
public class BloodGroupController implements Serializable {

    @GetMapping(UtilityRoute.BLOOD_GROUP)
    public List<DropdownListResource> getAllBloodGroup() {
        List<BloodGroup> group = Arrays.asList(BloodGroup.values());
        return group.stream()
                .sorted(Comparator.comparing(BloodGroup::getValue))
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private DropdownListResource convert(BloodGroup bg) {
        return DropdownListResource.builder()
                .id(bg.ordinal())
                .name(bg.getValue())
                .build();
    }
}
