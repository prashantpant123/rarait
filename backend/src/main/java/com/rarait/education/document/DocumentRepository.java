package com.rarait.education.document;

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
public interface DocumentRepository extends BaseJpaRepository<Document, Long> {

    @Query("SELECT d FROM Document d WHERE d.institute.id= :instituteId AND d.userId= :userId " +
            "AND d.type= :type AND d.status= :status")
    List<Document> findAllByInstituteAndUser(@Param("instituteId") int instituteId,
                                             @Param("userId") long userId,
                                             @Param("type") DocumentType type,
                                             @Param("status") Status status);

    @Query("SELECT d FROM Document d WHERE d.institute.id= :instituteId AND d.filename= :filename AND d.userId= :userId")
    Optional<Document> findOneByInstituteAndUser(@Param("instituteId") int instituteId,
                                                 @Param("filename") String username,
                                                 @Param("userId") long userId);

    @Query("SELECT d FROM Document d WHERE d.institute.id= :instituteId AND d.filename= :filename")
    Optional<Document> findOneByInstitute(@Param("instituteId") int instituteId,
                                          @Param("filename") String username);
}
