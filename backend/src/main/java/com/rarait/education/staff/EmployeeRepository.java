package com.rarait.education.staff;

import com.rarait.education.staff.impl.Employee;
import com.rarait.education.staff.impl.EmployeeType;
import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface EmployeeRepository extends BaseJpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.institute.id= :institute")
    Page<Employee> findAllByInstitute(@Param("institute") int institute,
                                      Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.institute.id= :institute AND e.type= :type")
    Page<Employee> findAllByInstituteAndType(@Param("institute") int institute,
                                              @Param("type") EmployeeType type,
                                              Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.institute.id= :institute AND e.id= :id")
    Optional<Employee> findOneByInstituteAndId(@Param("institute") int institute,
                                               @Param("id") long employeeId);
}
