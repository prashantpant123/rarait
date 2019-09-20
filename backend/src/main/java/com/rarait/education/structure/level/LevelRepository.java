package com.rarait.education.structure.level;

import com.rarait.education.structure.level.impl.Level;
import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface LevelRepository extends BaseJpaRepository<Level, Integer> {

    @Query("SELECT l FROM Level l WHERE l.institute.id=?1 AND l.id=?2")
    Level findByIdAndInstituteId(int instituteId, int levelId);

    @Query("SELECT l FROM Level l WHERE l.institute.id= :institute")
    Page<Level> findAllByInstitute(@Param("institute") int instituteId, Pageable pageable);

    @Query("SELECT l FROM Level l WHERE l.institute.id= :institute ORDER BY l.name")
    List<Level> findForDropdownByInstitute(@Param("institute") int instituteId);
}