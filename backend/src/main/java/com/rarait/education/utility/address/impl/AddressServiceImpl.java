package com.rarait.education.utility.address.impl;

import com.rarait.education.utility.address.spi.AddressRepository;
import com.rarait.education.utility.address.spi.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void addAddress(AddressCreateRequest request) {
        Address address = new Address();
        address.setName(request.getName());
        address.setFullAddress(request.getFullAddress());
        address.setType(request.getType());
        address.setCreatedDate(new Date());
        addressRepository.save(address);
    }

    @Override
    public Address getById(Integer addressId) {
        return addressRepository.findOneById(addressId);
    }

    @Override
    public List<AddressResource> getAllStates() {
        return AddressConverter.convertToList(addressRepository.findAllByGeoRegionType(GeoRegionType.STATE));
    }

    @Override
    public List<AddressResource> getAllAddressByTypeAndParent(Integer parentId, GeoRegionType geoRegionType) {
        return AddressConverter.convertToList(addressRepository.findAllByGeoRegionTypeAndParentId(geoRegionType, parentId));
    }
}