package com.rarait.education.student.impl;

import com.rarait.education.document.Document;
import com.rarait.education.document.DocumentService;
import com.rarait.education.document.DocumentType;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.structure.level.LevelService;
import com.rarait.education.shared.*;
import com.rarait.education.structure.level.SectionService;
import com.rarait.education.student.StudentRepository;
import com.rarait.education.student.StudentService;
import com.rarait.education.student.StudentValidator;

import com.rarait.education.student.resource.*;
import com.rarait.education.summary.SummaryService;
import com.rarait.education.utility.bloodgroup.BloodGroup;
import com.rarait.education.utility.address.spi.AddressService;
import com.rarait.education.utility.nationality.NationalityService;
import com.rarait.education.utility.occupation.spi.OccupationService;
import com.rarait.education.vehicle.TransportRouteService;
import com.rarait.framework.date.CalendarConversion;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.InputUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final InstituteLoginSession instituteLoginSession;
    private final OccupationService occupationService;
    private final TransportRouteService transportRouteService;
    private final LevelService levelService;
    private final StudentValidator studentValidator;
    private final SummaryService summaryService;
    private final SectionService sectionService;
    private final DocumentService documentService;
    private final NationalityService nationalityService;
    private final String FULLNAME = "full_name";
    private final String REGISTRATION_ID = "registration_id";

    @Value(AppProperties.DOMAIN_URL)
    private String domain;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              OccupationService occupationService,
                              TransportRouteService transportRouteService,
                              LevelService levelService,
                              SectionService sectionService,
                              InstituteLoginSession instituteLoginSession,
                              StudentValidator studentValidator,
                              SummaryService summaryService,
                              DocumentService documentService,
                              NationalityService nationalityService) {
        this.studentRepository = studentRepository;
        this.occupationService = occupationService;
        this.transportRouteService = transportRouteService;
        this.levelService = levelService;
        this.sectionService = sectionService;
        this.instituteLoginSession = instituteLoginSession;
        this.studentValidator = studentValidator;
        this.summaryService = summaryService;
        this.documentService = documentService;
        this.nationalityService = nationalityService;
    }

    @Override
    @Transactional
    public StudentCreateResponse addStudent(StudentRegisterRequest request) {
        studentValidator.validate(request);

        Institute institute = instituteLoginSession.getCurrentInstitute();

        Student student = new Student();
        student.setFirstname(request.getFirstName());
        student.setLastname(request.getLastName());
        student.setGender(Gender.valueOf(request.getGender()).get());
        student.setDateOfBirthAd(request.getDateOfBirth());
        student.setDateOfBirthBs(CalendarConversion.adToBs(request.getDateOfBirth()));
        student.setRollNumber(request.getRollNumber());
        student.setAddress(request.getAddress());

        student.setFatherName(request.getFatherName());
        student.setFatherContact(request.getFatherContact());
        student.setFatherOccupation(occupationService.findOneById(request.getFatherOccupationId()));
        student.setMotherName(request.getMotherName());
        student.setMotherContact(request.getMotherContact());
        student.setMotherOccupation(occupationService.findOneById(request.getMotherOccupationId()));
        student.setGuardian(request.getGuardianName());
        student.setGuardianContact(request.getGuardianContact());

        student.setLevel(levelService.findOneByIdAndInstitute(institute.getId(), request.getClassId()));
        student.setSection(sectionService.findOneByIdAndInstitute(institute.getId(), request.getSectionId()));
        student.setPhotoPath(null);
        student.setWeight(request.getWeight());
        student.setHeight(request.getHeight());

        if (request.getBusRouteId() > 0) {
            student.setTransportRoute(transportRouteService.findOneByIdAndInstitute(institute.getId(), request.getBusRouteId()));
        }
        student.setEnrolledDate(new Date());
        student.setRegistrationId(institute.getRegistrationPrefix() + request.getRegistrationNumber());
        student.setInstitute(institute);
        student.setNationality(nationalityService.findOneById(request.getNationalityId()));
        student.setBloodGroup(BloodGroup.getEnumFromOrdinal(request.getBloodGroup()));
        student = studentRepository.save(student);

        summaryService.updateStudent();
        return StudentCreateResponse.builder()
                .id(student.getId())
                .message("Student registered successfully")
                .build();
    }

    @Override
    public void updateStudent(StudentUpdateResource request) {
        Optional<Student> students = Optional.ofNullable(studentRepository.findByRollNumber(request.getRegistrationId()));
        Student student = students.orElseThrow(() -> new ClientRestException("Student with " + request.getRegistrationId() + " not found"));
//        student.setRemarks(request);
        if (request.getBusRouteId() > 0)
            student.setTransportRoute(transportRouteService.findOneById(request.getBusRouteId()));
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public PagedResponse<StudentResponse> findAllStudentWithFilter(int pageNumber, Integer levelId,
                                                                   String sortField, Boolean ascend) {
        Institute institute = instituteLoginSession.getCurrentInstitute();

        Page<Student> students = studentRepository.findAll((Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                    Path<Long> path = root.get("institute").get("id");
                    Predicate p = cb.equal(path, institute.getId());
                    if (levelId != null && levelId > 0) {
                        p = cb.and(p, cb.equal(root.get("level").get("id"), levelId));
                    }
                    return p;
                }, InputUtil.isEmpty(sortField) || InputUtil.isEmpty(ascend) ? PagedRequest.get(pageNumber, Sort.by("id").descending()) :
                        PagedRequest.get(pageNumber, ascend ? Sort.by("rollNumber").ascending() : Sort.by("rollNumber").descending())
        );
        return new PagedResponse<>(students.getTotalElements(),
                students.getTotalPages(),
                pageNumber,
                StudentConverter.convertAll(students.getContent()));
    }

    @Override
    @Transactional
    public PagedResponse<StudentResponse> searchByFilter(int pageNumber, Integer levelId, String searchParameter, String searchValue) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        if (InputUtil.isEmpty(searchValue)) {
            throw new ClientRestException("Please provide the data to search");
        }

        Page<Student> students = studentRepository.findAll((Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Path<Long> path = root.get("institute").get("id");
            Predicate p = cb.equal(path, institute.getId());
            if (levelId != null && levelId > 0) {
                p = cb.and(p, cb.equal(root.get("level").get("id"), levelId));
            }
            if (searchParameter != null && !searchParameter.isEmpty()) {
                if (searchParameter.equals(FULLNAME)) {
                    if (searchValue.length() >= 3) {
                        Predicate name = cb.or(cb.like(root.get("firstname"), "%" + searchValue + "%"),
                                cb.like(root.get("lastname"), "%" + searchValue + "%"));
                        p = cb.and(p, name);
                    }
                } else if (searchParameter.equals(REGISTRATION_ID)) {
                    if (searchValue.length() >= 3) {
                        Predicate name = cb.or(cb.like(root.get("registrationId"), "%" + searchValue + "%"));
                        p = cb.and(p, name);
                    }
                }
            }
            return p;
        }, searchParameter.equals(FULLNAME) ? PagedRequest.get(pageNumber, Sort.by("firstname")) : PagedRequest.get(pageNumber, Sort.by("registrationId")));
        return new PagedResponse<>(students.getTotalElements(),
                students.getTotalPages(),
                pageNumber,
                StudentConverter.convertAll(students.getContent()));
    }

    @Override
    public Page<Student> findAllWithLevelAndInstitute(int pageNumber, Integer levelId, int instituteId) {
        return studentRepository.findAllByInstituteAndLevelId(
                instituteId, levelId,
                PagedRequest.get(pageNumber, Sort.by("rollNumber")));
    }

    @Override
    @Transactional
    public StudentDetailResource findDetailById(Long studentId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Student student = studentRepository.findOneByInstituteAndId(studentId, institute.getId());
        if (studentId == null || student == null) {
            throw new ClientRestException("Student not found");
        }
        List<Document> documents = documentService.getDocumentForInstitute(studentId, DocumentType.STUDENT_PICTURE, institute.getId());
        return StudentConverter.convertDetail(student, documents == null || documents.isEmpty() ? null : documents.get(0), domain);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Student findStudent(Long studentId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Student student = studentRepository.findOneByInstituteAndId(studentId, institute.getId());
        if (studentId == null || student == null) {
            throw new ClientRestException("Student not found");
        }
        return student;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Student findByIdAndInstitute(long studentId, int instituteId) {
        Optional<Student> student = Optional.of(studentRepository.findOneByInstituteAndId(studentId, instituteId));
        return student.orElseThrow(() -> new ClientRestException("Student not found"));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadDocument(MultipartFile file, DocumentType type, Long studentId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Student student = findByIdAndInstitute(studentId, institute.getId());
        documentService.createDocumentForInstitute(file, type, student.getId(), institute);
    }

    @Override
    public boolean checkRegistrationNumber(String registrationNumber) {
        Optional<Student> student = studentRepository.findOneByRegistrationId(registrationNumber);
        return student.isPresent();
    }

}