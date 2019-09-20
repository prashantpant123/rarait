package com.rarait.education.pdf;

import com.rarait.education.staff.impl.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepos extends JpaRepository<Employee,Long> {



//    @Query("SELECT e FROM Employee e where e.id=:id")
//    Employee findById(@Param("id") Long id);



    @Query("SELECT e FROM Employee e WHERE  e.id= :id")
    Optional<Employee> findOneById(@Param("id") long employeeId);
}
