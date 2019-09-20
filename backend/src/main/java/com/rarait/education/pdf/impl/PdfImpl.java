package com.rarait.education.pdf.impl;

import com.rarait.education.pdf.EmployeeRepos;
import com.rarait.education.pdf.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Iterator;
import java.util.Map;

@Component
public class PdfImpl implements PdfService {

    @Autowired
    private EmployeeRepos employeeRepos;
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void createStaffProfile(String staffTemplate, Map map, ServletContext servletContext, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Assert.notNull(staffTemplate, "The templateName can not be null");
        Context ctx = new Context();
        if (map != null) {
            Iterator itMap = map.entrySet().iterator();
            while (itMap.hasNext()) {
                Map.Entry pair = (Map.Entry) itMap.next();
                ctx.setVariable(pair.getKey().toString(), pair.getValue());
            }
        }
        String processedHtml = templateEngine.process(staffTemplate, ctx);
        FileOutputStream os = null;
        String fileName = "filename";

        try {
            String outputFile = fileName + ".pdf";
            os = new FileOutputStream(outputFile);
            System.out.println(outputFile);

            ITextRenderer renderer = new ITextRenderer();
            //String baseUrl = FileSystems.getDefault().getPath("src","main","webapp","resources","images").toUri().toURL().toString();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();
            System.out.println("PDF created successfully");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void createStudentProfile(String studentTemplate, Map map, ServletContext servletContext, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Context context = new Context();
        Assert.notNull(studentTemplate, "The templateName can not be null");
        if (map != null) {
            Iterator itMap = map.entrySet().iterator();
            while (itMap.hasNext()) {
                Map.Entry pair = (Map.Entry) itMap.next();
                context.setVariable(pair.getKey().toString(), pair.getValue());
            }
        }
        String processedHtml = templateEngine.process(studentTemplate, context);
        FileOutputStream os = null;
        String fileName = "filename";
        try {
            String outputFile = fileName + ".pdf";
            os = new FileOutputStream(outputFile);
            ITextRenderer renderer = new ITextRenderer();
            //String baseUrl = FileSystems.getDefault().getPath("src","main","webapp","resources","images").toUri().toURL().toString();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();
            System.out.println("PDF created successfully");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}





