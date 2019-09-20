package com.rarait.education.utility.address.impl;

import com.rarait.education.shared.route.UtilityRoute;
import com.rarait.education.utility.address.spi.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(UtilityRoute.ADDRESS_STATE)
    public List<AddressResource> getAllState() {
        return addressService.getAllStates();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(UtilityRoute.ADDRESS_DISTRICT)
    public List<AddressResource> getAllDistrictOfState(@RequestParam("state_id") Integer stateId) {
        return addressService.getAllAddressByTypeAndParent(stateId, GeoRegionType.DISTRICT);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(UtilityRoute.ADDRESS_MUNICIPALITY)
    public List<AddressResource> getAllMunicipalityOfDistrict(@RequestParam("district_id") Integer stateId) {
        return addressService.getAllAddressByTypeAndParent(stateId, GeoRegionType.MUNICIPALITY_VDC);
    }
}
