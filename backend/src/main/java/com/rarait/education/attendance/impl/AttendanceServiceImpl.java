package com.rarait.education.attendance.impl;

import com.rarait.education.attendance.AttendanceRepository;
import com.rarait.education.attendance.AttendanceService;
import com.rarait.education.attendance.AttendanceStatus;
import com.rarait.education.attendance.AttendanceValidator;
import com.rarait.education.attendance.resource.AttendanceCreateListRequest;
import com.rarait.education.attendance.resource.AttendanceCreateRequest;
import com.rarait.education.attendance.resource.AttendanceDropdownResource;
import com.rarait.education.attendance.resource.AttendanceResponse;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.structure.level.LevelService;
import com.rarait.education.structure.level.impl.Level;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.shared.PagedRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.student.StudentService;
import com.rarait.framework.date.CalendarConversion;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceValidator attendanceValidator;
    private final StudentService studentService;
    private final LevelService levelService;
    private final InstituteLoginSession instituteLoginSession;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
                                 AttendanceValidator attendanceValidator,
                                 StudentService studentService,
                                 LevelService levelService,
                                 InstituteLoginSession instituteLoginSession) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceValidator = attendanceValidator;
        this.studentService = studentService;
        this.levelService = levelService;
        this.instituteLoginSession = instituteLoginSession;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createAttendances(AttendanceCreateListRequest request) {
        log.info("request: {}", request.toString());
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Level level = levelService.findOneByIdAndInstitute(request.getLevelId(), institute.getId());
        request.getData().stream()
                .forEach(req -> {
                    if (req.getId() > 0)
                        updateAttendance(req, institute.getId());
                    else
                        addAttendance(req, institute, request.getAttendanceDate());
                });
    }

    private void addAttendance(AttendanceCreateRequest request, Institute institute, Date attendanceDate) {
        attendanceValidator.validate(request);
        Attendance attendance = new Attendance();
        attendance.setAttendanceDate(attendanceDate);
        attendance.setAttendanceDateBs(CalendarConversion.adToBs(attendanceDate));
        attendance.setStudent(studentService.findByIdAndInstitute(request.getStudentId(), institute.getId()));
        attendance.setStatus(AttendanceStatus.getEnum(request.getStatus()));
        attendance.setRemarks(request.getRemarks());
        attendance.setInstitute(institute);
        attendanceRepository.save(attendance);
    }

    private void updateAttendance(AttendanceCreateRequest request, int instituteId) {
        Optional<Attendance> attendances = Optional.of(attendanceRepository.findOneByIdAndInstitute(request.getId(), instituteId));
        Attendance attendance = attendances.orElseThrow(() -> new ClientRestException("Attendance record not found"));
        attendance.setStatus(AttendanceStatus.getEnum(request.getStatus()));
        attendance.setRemarks(request.getRemarks());
        attendanceRepository.save(attendance);
    }

    @Override
    @Transactional
    public PagedResponse<AttendanceResponse> findAllAttendanceWithFilter(int pageNumber, Integer classId, Date attendanceDate) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Level level = levelService.findOneByIdAndInstitute(classId, institute.getId());
        Page<AttendanceResponse> attendances = attendanceRepository.findRecordByInstituteIdAndLevelId(
                institute.getId(),
                level.getId(),
                DateUtil.startOfDay(attendanceDate),
                DateUtil.endOfDay(attendanceDate),
                PagedRequest.getLarge(pageNumber));
        return new PagedResponse<>(attendances.getTotalElements(),
                attendances.getTotalPages(),
                pageNumber,
                attendances.getContent());
    }

    @Override
    public List<AttendanceDropdownResource> getAllForDropdown() {
        return AttendanceConvert.convertTypeList();
    }
}
