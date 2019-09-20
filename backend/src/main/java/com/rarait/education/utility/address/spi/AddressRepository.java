package com.rarait.education.utility.address.spi;

import com.rarait.education.utility.address.impl.GeoRegionType;
import com.rarait.education.utility.address.impl.Address;
import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface AddressRepository extends BaseJpaRepository<Address, Integer> {

    @Query("SELECT a FROM Address a WHERE a.type=?1 ORDER BY a.name")
    List<Address> findAllByGeoRegionType(GeoRegionType type);

    @Query("SELECT a FROM Address a WHERE a.type=?1 AND a.parentId=?2 ORDER BY a.name")
    List<Address> findAllByGeoRegionTypeAndParentId(GeoRegionType type, Integer parentId);

    @Query("SELECT a FROM Address a WHERE a.id=?1")
    Address findOneById(Integer addressId);
}