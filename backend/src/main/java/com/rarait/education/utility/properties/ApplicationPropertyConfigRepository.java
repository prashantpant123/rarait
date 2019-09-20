package com.rarait.education.utility.properties;

import com.rarait.framework.repository.BaseJpaRepository;
import com.rarait.framework.shared.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface ApplicationPropertyConfigRepository extends BaseJpaRepository<ApplicationPropertyConfig, Short> {

    ApplicationPropertyConfig findByType(PropertyType type);

    @Query("SELECT a.value FROM ApplicationPropertyConfig a WHERE a.type=:propType and a.status=:status")
    String findValueByTypeAndStatus(@Param("propType") PropertyType type,
                                    @Param("status") Status status);

    @Query("SELECT a FROM ApplicationPropertyConfig a WHERE a.type=:propType and a.status=:status")
    ApplicationPropertyConfig findByTypeAndStatus(@Param("propType") PropertyType type,
                                                  @Param("status") Status status);
}