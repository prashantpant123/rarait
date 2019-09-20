package com.rarait.education.student;

import com.rarait.education.student.impl.Student;
import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface StudentRepository extends BaseJpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.rollNumber= :rollNumber")
    Student findByRollNumber(@Param("rollNumber") String rollNumber);

    @Query("SELECT s FROM Student s WHERE s.institute.id = :instituteId")
    Page<Student> findAllByInstitute(@Param("instituteId") int instituteId,
                                     Pageable pageable);

    @Query("SELECT s FROM Student s JOIN Institute i ON i.id=s.institute.id " +
            "JOIN Level l ON s.level.id=l.id " +
            "WHERE i.id = :instituteId " +
            "AND l.id= :levelId ORDER BY s.rollNumber")
    Page<Student> findAllByInstituteAndLevelId(@Param("instituteId") int instituteId,
                                               @Param("levelId") int levelId,
                                               Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.institute.id= :instituteId AND s.id= :studentId")
    Student findOneByInstituteAndId(@Param("studentId") long studentId,
                                    @Param("instituteId") int instituteId);

    @Query("SELECT s FROM Student s WHERE s.registrationId= :registrationId")
    Optional<Student> findOneByRegistrationId(@Param("registrationId") String registrationId);
}