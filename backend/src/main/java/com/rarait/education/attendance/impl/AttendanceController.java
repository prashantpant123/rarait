package com.rarait.education.attendance.impl;

import com.rarait.education.attendance.AttendanceService;
import com.rarait.education.attendance.resource.AttendanceCreateListRequest;
import com.rarait.education.attendance.resource.AttendanceDropdownResource;
import com.rarait.education.attendance.resource.AttendanceResponse;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.route.InstituteRoute;
import com.rarait.framework.shared.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@RestController
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.ATTENDANCE_TYPE)
    public List<AttendanceDropdownResource> getAllDropdownList() {
        return attendanceService.getAllForDropdown();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.ATTENDANCE)
    public PagedResponse<AttendanceResponse> getAllAttendance(
            @RequestParam(value = "class_id", required = false) Integer classId,
            @RequestParam(value = "attendance", required = false) String attendanceDate,
            @RequestParam(value = "page") int pageNumber) {
        return attendanceService.findAllAttendanceWithFilter(pageNumber, classId, DateUtil.convertStringToDate(attendanceDate));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(InstituteRoute.ATTENDANCE)
    public void createAttendance(@RequestBody AttendanceCreateListRequest request) {
        attendanceService.createAttendances(request);
    }
}
