package com.rarait.education.attendance;

import com.rarait.education.attendance.resource.AttendanceCreateListRequest;
import com.rarait.education.attendance.resource.AttendanceCreateRequest;
import com.rarait.education.attendance.resource.AttendanceDropdownResource;
import com.rarait.education.attendance.resource.AttendanceResponse;
import com.rarait.education.shared.PagedResponse;

import java.util.Date;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface AttendanceService {

    void createAttendances(AttendanceCreateListRequest request);

    PagedResponse<AttendanceResponse> findAllAttendanceWithFilter(int pageNumber, Integer classId, Date attendanceDate);

    List<AttendanceDropdownResource> getAllForDropdown();
}
