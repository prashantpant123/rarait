package com.rarait.education.attendance;

import com.rarait.education.attendance.impl.Attendance;
import com.rarait.education.attendance.resource.AttendanceResponse;
import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface AttendanceRepository extends BaseJpaRepository<Attendance, Long> {

    @Query(nativeQuery = true, name = "findAllAttendanceRecordWithFilter")
    Page<AttendanceResponse> findRecordByInstituteIdAndLevelId(@Param("instituteId") int instituteId,
                                                               @Param("classId") int levelId,
                                                               @Param("startDate") Date startDate,
                                                               @Param("endDate") Date endDate,
                                                               Pageable pageable);

    @Query("SELECT a FROM Attendance a WHERE a.institute.id= :instituteId AND a.id= :id")
    Attendance findOneByIdAndInstitute(@Param("id") long id,
                                       @Param("instituteId") int instituteId);
}
