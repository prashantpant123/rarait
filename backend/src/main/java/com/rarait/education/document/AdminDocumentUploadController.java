package com.rarait.education.document;

import com.rarait.education.institute.InstituteService;
import com.rarait.education.shared.route.AdminRoute;
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
public class AdminDocumentUploadController {

    private final DocumentService documentService;
    private final InstituteService instituteService;

    public AdminDocumentUploadController(DocumentService documentService,
                                         InstituteService instituteService) {
        this.documentService = documentService;
        this.instituteService = instituteService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = AdminRoute.INSTITUTE_FILE)
    public void uploadInstituteDocument(@RequestParam("logo") MultipartFile file,
                                        @RequestParam("id") Integer instituteId) {
        instituteService.uploadDocumentForAdmin(file, DocumentType.LOGO, instituteId);
    }

    @GetMapping(AdminRoute.INSTITUTE_FILE_ID + "/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,
                                                 @PathVariable("institute_id") String instituteId,
                                                 HttpServletRequest request) {
        Resource resource = documentService.downloadFileForAdmin(fileName, BaseConversion.getInteger(instituteId));
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
