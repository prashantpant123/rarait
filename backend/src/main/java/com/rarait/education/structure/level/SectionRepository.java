package com.rarait.education.structure.level;

import com.rarait.education.structure.level.impl.Section;
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
public interface SectionRepository extends BaseJpaRepository<Section, Integer> {

    @Query("SELECT s FROM Section s WHERE s.level.institute.id= :institute AND s.level.id= :levelId")
    Page<Section> findAllByInstituteAndLevel(@Param("institute") int instituteId,
                                             @Param("levelId") int levelId,
                                             Pageable pageable);

    @Query("SELECT s FROM Section s WHERE s.level.institute.id= :institute")
    Page<Section> findAllByInstitute(@Param("institute") int instituteId,
                                             Pageable pageable);

    @Query("SELECT s FROM Section s WHERE s.level.institute.id= :institute AND s.id= :sectionId")
    Section findOneByInstituteAndId(@Param("institute") int instituteId,
                                    @Param("sectionId") int sectionId);

    @Query("SELECT s FROM Section s WHERE s.level.id= :levelId AND s.level.institute.id= :instituteId")
    List<Section> findAllByInstituteAndLevel(@Param("instituteId") int instituteId,
                                             @Param("levelId") int levelId);

    @Query("SELECT s FROM Section s WHERE s.level.institute.id= :institute ORDER BY s.level.name")
    List<Section> findAllByInstitute(@Param("institute") int instituteId);
}
