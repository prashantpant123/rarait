package com.rarait.education.pdf.controller;

import com.rarait.education.institute.InstituteLoginService;
import com.rarait.education.institute.InstituteService;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.institute.resource.InstituteBasicInfo;
import com.rarait.education.institute.resource.InstituteDetailResource;
import com.rarait.education.institute.resource.InstituteResponse;
import com.rarait.education.login.auth.JwtProvider;
import com.rarait.education.pdf.EmployeeRepos;
import com.rarait.education.pdf.PdfService;
import com.rarait.education.pdf.impl.PdfImpl;
import com.rarait.education.shared.route.InstituteRoute;
import com.rarait.education.staff.EmployeeService;
import com.rarait.education.staff.impl.Employee;
import com.rarait.education.staff.resource.EmployeeDetailResource;
import com.rarait.education.student.StudentService;
import com.rarait.education.student.resource.StudentDetailResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.print.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.rarait.education.shared.route.InstituteRoute.PDF;
import static com.rarait.education.shared.route.InstituteRoute.STUDENT_PDF;

@Slf4j
@Controller
public class PdfController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private InstituteService instituteService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private ServletContext servletContext;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(PDF)
    public void createPdf(@PathVariable("employee_id") Integer id,
                          HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        log.info("API called from front end ");
        EmployeeDetailResource e = employeeService.getEmployeeDetail(id);
        InstituteBasicInfo instituteBasicInfo= instituteService.findBasicInfo();

        Map<String, String> data = new HashMap<String, String>();
        data.put("schoolLogo",instituteBasicInfo.getLogo());
        data.put("schoolName",instituteBasicInfo.getName());
        data.put("picture",e.getPicture());
        data.put("address",instituteBasicInfo.getAddress());
        data.put("landline",instituteBasicInfo.getLandline());
        data.put("firstName",e.getFirstName());
        data.put("lastName",e.getLastName());
        data.put("gender",e.getGender().toLowerCase());
        String input=e.getDateOfBirth().toString();
        String dob=input.substring(0,10);
        data.put("dob",dob);
        data.put("dobBs",e.getDobBs());
        data.put("nationality",e.getNationality());
        data.put("bloodGroup",e.getBloodGroup());
        data.put("currentAddress",e.getCurrentAddress());
        data.put("permanentAddress",e.getPermanentAddress());
        data.put("primaryContact",e.getPrimaryContact());
        data.put("secondaryContact",e.getSecondaryContact());
        data.put("role",e.getEmployeeType());
        data.put("designation",e.getDesignation());
        String input1=e.getJoiningDate().toString();
        String joiningDate=input1.substring(0,10);
        data.put("joiningDate",joiningDate);
        data.put("qualification",e.getQualification());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        data.put("publishedDate",dateFormat.format(date));

        pdfService.createStaffProfile("staffTemplate", data, servletContext, httpServletRequest, httpServletResponse);
        String fullPath = "filename" + ".pdf";
        log.info("Fullpath to download pdf selected.");
        fileDownload(fullPath, httpServletResponse, "filename.pdf");
        log.info("Pdf download finished.");
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(STUDENT_PDF)
    public void createStudentPdf(@PathVariable("student_id") Long id, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        log.info("API called from front end ");
        StudentDetailResource sdr = studentService.findDetailById(id);
        InstituteBasicInfo instituteBasicInfo= instituteService.findBasicInfo();
        Map<String, String> data = new HashMap<String, String>();
        data.put("schoolLogo",instituteBasicInfo.getLogo());
        data.put("schoolName",instituteBasicInfo.getName());
        data.put("picture",sdr.getPicture());
        data.put("address",instituteBasicInfo.getAddress());
        data.put("landline",instituteBasicInfo.getLandline());
        data.put("firstName",sdr.getFirstName());
        data.put("lastName",sdr.getLastName());
        data.put("level", sdr.getLevel());
        log.info(sdr.getLevel());
        data.put("rollNumber", sdr.getRollNumber());
        data.put("registrationNo", sdr.getRollNumber());
        String e = sdr.getEnrolledDate().toString();
        String eDate= e.substring(0,Math.min(e.length(), 10));
        data.put("enrolledDate",eDate);
//        data.put("enrolledDate", sdr.getEnrolledDate());
        data.put("gender", sdr.getGender().toLowerCase());
        String input=sdr.getDateOfBirthAd().toString();
        String dobAd=input.substring(0,Math.min(input.length(), 10));
        data.put("dobAd",dobAd);
        String input1=sdr.getDateOfBirthBs().toString();
        String dobBs=input1.substring(0,Math.min(input1.length(), 10));
        data.put("dobBs",dobBs);
        data.put("nationality",sdr.getNationality());
        data.put("address",sdr.getAddress());
        data.put("fatherName", sdr.getFatherName());
        data.put("fatherOccupation",sdr.getFatherOccupation());
        data.put("fatherContact",sdr.getFatherContact());
        data.put("motherName", sdr.getMotherName());
        data.put("motherOccupation", sdr.getMotherOccupation());
        data.put("motherContact",sdr.getMotherContact());
        data.put("guardianName",sdr.getGuardianName());
        data.put("guardianContact",sdr.getGuardianContact());
        data.put("transport",sdr.getTransport());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        data.put("publishedDate",dateFormat.format(date));
        pdfService.createStudentProfile("studentTemplate", data, servletContext, httpServletRequest, httpServletResponse);
        String fullPath = "filename" + ".pdf";
        log.info("Fullpath to download pdf selected.");
        fileDownload(fullPath, httpServletResponse, "filename.pdf");
        log.info("Pdf download finished.");
    }

    private void fileDownload(String fullPath, HttpServletResponse httpServletResponse, String fileName) {
        File file = new File(fullPath);
        final int BUFFER_SIZE = 4096;
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                String mimeType = servletContext.getMimeType(fullPath);
                httpServletResponse.setContentType(mimeType);
                httpServletResponse.setHeader("x-filename", "fileName");
                httpServletResponse.setHeader("Access-Control-Expose-Headers", "x-filename");
                httpServletResponse.setHeader("content-disposition", "attachment;filename=" + fileName);
                OutputStream outputStream = httpServletResponse.getOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                fileInputStream.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}







