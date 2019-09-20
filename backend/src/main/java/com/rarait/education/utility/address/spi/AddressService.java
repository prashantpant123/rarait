package com.rarait.education.utility.address.spi;

import com.rarait.education.utility.address.impl.AddressCreateRequest;
import com.rarait.education.utility.address.impl.AddressResource;
import com.rarait.education.utility.address.impl.GeoRegionType;
import com.rarait.education.utility.address.impl.Address;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since Aug 28, 2018
 */
public interface AddressService {

    void addAddress(AddressCreateRequest request);

    Address getById(Integer addressId);

    List<AddressResource> getAllStates();

    List<AddressResource> getAllAddressByTypeAndParent(Integer parentId, GeoRegionType geoRegionType);
}
