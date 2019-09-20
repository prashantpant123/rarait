package com.rarait.education.utility.nationality;

import com.rarait.framework.repository.BaseJpaRepository;
import com.rarait.framework.shared.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface NationalityRepository extends BaseJpaRepository<Nationality, Short> {

    @Query("SELECT n FROM Nationality n WHERE n.id= :id AND n.status= :status")
    Optional<Nationality> findOneByIdAndStatus(@Param("status") Status status,
                                               @Param("id") short id);

    @Query("SELECT n FROM Nationality n WHERE n.status= :status ORDER BY n.name")
    List<Nationality> findAllActive(@Param("status") Status status);

}
