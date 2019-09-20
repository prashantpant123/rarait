package com.rarait.education.document;

import com.rarait.education.institute.InstituteService;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.shared.route.AdminRoute;
import com.rarait.education.shared.route.InstituteRoute;
import com.rarait.education.utility.BaseConversion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Controller
public class InstituteDocumentUploadController {

    private final InstituteService instituteService;

    public InstituteDocumentUploadController(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = InstituteRoute.INSTITUTE_FILE)
    public void uploadInstituteDocument(@RequestParam("file") MultipartFile file,
                                        @RequestParam("type") String type,
                                        @RequestParam("id") Long userId) {
        DocumentType document = DocumentType.valueOf(type);
        instituteService.uploadDocumentForInstitute(file, document, userId);
    }

    @GetMapping(InstituteRoute.INSTITUTE_STAFF_FILE_ID + "/{fileName:.+}")
    public ResponseEntity<Resource> downloadStaffFile(@PathVariable String fileName,
                                                      @PathVariable("user_id") String userId,
                                                      @PathVariable("institute_id") String institute,
                                                      HttpServletRequest request) {
        Resource resource = instituteService.getDocument(fileName, BaseConversion.getLong(userId), BaseConversion.getInteger(institute));
        return convert(resource, request);
    }

    @GetMapping(InstituteRoute.INSTITUTE_FILE_ID + "/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,
                                                 @PathVariable("institute_id") String institute,
                                                 HttpServletRequest request) {
        Resource resource = instituteService.getDocument(fileName, BaseConversion.getInteger(institute));
        return convert(resource, request);
    }

    private ResponseEntity<Resource> convert(Resource resource, HttpServletRequest request) {
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ioe) {
            log.info("Could not determine file type.");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
