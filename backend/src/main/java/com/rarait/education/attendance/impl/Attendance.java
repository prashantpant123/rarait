package com.rarait.education.attendance.impl;

import com.rarait.education.attendance.AttendanceStatus;
import com.rarait.education.attendance.resource.AttendanceResponse;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.student.impl.Student;
import com.rarait.framework.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@SqlResultSetMapping(
        name = "findAllAttendanceRecords",
        classes = @ConstructorResult(
                targetClass = AttendanceResponse.class,
                columns = {
                        @ColumnResult(name = "id", type = BigInteger.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "roll", type = String.class),
                        @ColumnResult(name = "student_id", type = BigInteger.class),
                        @ColumnResult(name = "status", type = Integer.class),
                        @ColumnResult(name = "remarks", type = String.class)
                }
        )
)

@NamedNativeQuery(name = "findAllAttendanceRecordWithFilter",
        resultClass = AttendanceResponse.class,
        resultSetMapping = "findAllAttendanceRecords",
        query = "SELECT \n" +
                "    at.id as `id`,\n" +
                "    CONCAT(s.firstname, ' ', s.lastname) as `name`,\n" +
                "    s.roll_number as `roll`,\n" +
                "    s.id as student_id,\n" +
                "    at.status as status,\n" +
                "    at.remarks as remarks\n" +
                " FROM\n" +
                "    student s\n" +
                "        JOIN\n" +
                "    institute i ON i.id = s.institute_id\n" +
                "       LEFT JOIN\n" +
                "    (SELECT \n" +
                "        a.id, a.status, a.remarks, a.student_id\n" +
                "    FROM\n" +
                "        attendance a\n" +
                "    JOIN student s ON s.id = a.student_id\n" +
                "    WHERE\n" +
                "        a.attendance_date BETWEEN :startDate AND :endDate\n" +
                "            AND s.institute_id = :instituteId\n" +
                "            AND s.level_id = :classId) as at\n" +
                "            ON at.student_id = s.id\n" +
                "WHERE\n" +
                "    s.institute_id = :instituteId AND s.level_id = :classId\n" +
                "ORDER BY s.roll_number")
@Entity
@Getter
@Setter
@ToString
public class Attendance extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @Column(nullable = false)
    private AttendanceStatus status;

    private Date attendanceDate;

    @Column(length = 15)
    private String attendanceDateBs;

    @ManyToOne(fetch = FetchType.LAZY)
    private Institute institute;

    private String remarks;
}
