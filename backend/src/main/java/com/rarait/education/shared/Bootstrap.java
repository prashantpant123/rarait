package com.rarait.education.shared;

import com.rarait.education.login.AdminUserService;
import com.rarait.education.login.RoleRepository;
import com.rarait.education.login.impl.Role;
import com.rarait.education.login.impl.RoleName;
import com.rarait.education.login.resource.UserCreateRequest;
import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.education.utility.address.impl.Address;
import com.rarait.education.utility.address.impl.AddressCreateRequest;
import com.rarait.education.utility.address.impl.AddressResource;
import com.rarait.education.utility.address.impl.GeoRegionType;
import com.rarait.education.utility.address.spi.AddressService;
import com.rarait.education.utility.nationality.NationalityResource;
import com.rarait.education.utility.nationality.NationalityService;
import com.rarait.education.utility.occupation.impl.Occupation;
import com.rarait.education.utility.occupation.impl.OccupationCreateRequest;
import com.rarait.education.utility.occupation.impl.OccupationResource;
import com.rarait.education.utility.occupation.spi.OccupationService;
import com.rarait.framework.shared.ProfileNames;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Component
//@Profile({ProfileNames.UAT,ProfileNames.DEV})
public class Bootstrap {
    private String[] prepOccupation = {"IT Officer", "Teacher", "Government Officer"};
    private String[] states = {"Province 1", "Province 2", "Province 3", "Province 4", "Province 5", "Province 6"};
    private String[] nationality = {"Nepalese", "Indians", "Bangladeshis", "Afghans"};

    private final OccupationService occupationService;
    private final AdminUserService adminUserService;
    private final NationalityService nationalityService;
    private final AddressService addressService;
    private final RoleRepository roleRepository;

    @Autowired
    public Bootstrap(OccupationService occupationService,
                     AdminUserService adminUserService,
                     AddressService addressService,
                     NationalityService nationalityService,
                     RoleRepository roleRepository) {
        this.occupationService = occupationService;
        this.adminUserService = adminUserService;
        this.addressService = addressService;
        this.nationalityService = nationalityService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        addRoles();
        createSuperAdmin();
        generateOccupation();
        createState();
        createNationality();
    }

    private void addRoles() {
        List<Role> roles = roleRepository.findAll();
        if (roles == null || roles.isEmpty()) {
            EnumUtils.getEnumList(RoleName.class).stream()
                    .forEach(e -> {
                        roleRepository.save(new Role(e));
                    });
        }
    }

    private void generateOccupation() {
        List<OccupationResource> lists = occupationService.findAll();
        if (lists == null || lists.isEmpty()) {
            for (String o : prepOccupation) {
                OccupationCreateRequest request = new OccupationCreateRequest();
                request.setDisplayName(o);
                occupationService.addOccupation(request);
            }
        }
    }

    private void createSuperAdmin() {
        String username = "admin@webvidhyalaya.com";
        if (adminUserService.findByUsername(username) == null) {
            UserCreateRequest request = new UserCreateRequest();
            request.setUsername(username);
            request.setPassword("webVidhyalaya@1234".toCharArray());
            request.setRoleNames(Arrays.asList(RoleName.ROLE_ADMIN));
            adminUserService.addCredential(request);
        }
    }

    private void createState() {
        List<AddressResource> ar = addressService.getAllStates();
        if (ar == null || ar.isEmpty()) {
            for (String s : states) {
                AddressCreateRequest request = new AddressCreateRequest(s, s, GeoRegionType.STATE);
                addressService.addAddress(request);
            }
        }
    }

    private void createNationality() {
        List<DropdownListResource> nr = nationalityService.findAllNationality();
        log.info("Nationality {}", nr.toString());
        if (nr == null || nr.isEmpty()) {
            for (String s : nationality) {
                nationalityService.addNationality(s);
            }
        }
    }
}
