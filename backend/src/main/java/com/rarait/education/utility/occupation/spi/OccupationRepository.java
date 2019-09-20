package com.rarait.education.utility.occupation.spi;

import com.rarait.education.utility.occupation.impl.Occupation;
import com.rarait.framework.repository.BaseJpaRepository;
import com.rarait.framework.shared.Status;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface OccupationRepository extends BaseJpaRepository<Occupation, Short> {

    @Query("SELECT o FROM Occupation o WHERE o.status=?1 ORDER BY o.name")
    List<Occupation> findAllByStatus(Status status);

    @Query("SELECT o FROM Occupation o WHERE o.id=?1 AND o.status=?2")
    Occupation findOneById(Short occupationId, Status status);
}
