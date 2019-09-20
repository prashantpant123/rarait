package com.rarait.education.pdf;




import com.rarait.education.staff.impl.Employee;
import org.springframework.core.io.Resource;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

public interface PdfService {
public void createStaffProfile(String staffTemplate,Map map,ServletContext servletContext, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;
    public void createStudentProfile(String studentTemplate,Map map,ServletContext servletContext, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;

}
