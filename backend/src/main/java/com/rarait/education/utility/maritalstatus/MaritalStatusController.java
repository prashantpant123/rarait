package com.rarait.education.utility.maritalstatus;

import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.education.shared.route.UtilityRoute;
import com.rarait.education.staff.impl.MaritalStatus;
import com.rarait.education.utility.bloodgroup.BloodGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class MaritalStatusController {

    @GetMapping(UtilityRoute.MARITAL_STATUS)
    public List<DropdownListResource> findAllMaritalStatus(){
        List<MaritalStatus> group = Arrays.asList(MaritalStatus.values());
        return group.stream()
                .sorted(Comparator.comparing(MaritalStatus::getValue))
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private DropdownListResource convert(MaritalStatus ms) {
        return DropdownListResource.builder()
                .id(ms.getValue())
                .name(ms.name())
                .build();
    }
}
