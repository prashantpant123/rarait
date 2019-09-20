package com.rarait.education.document;

import com.rarait.education.shared.route.InstituteRoute;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@RestController
public class DocumentController {

    private DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }
//
//    @PostMapping(InstituteRoute.FILE_UPLOAD)
//    public void uploadLogo(@RequestParam("file") MultipartFile file,
//                           @RequestParam("type") String type,
//                           @RequestParam("user_id") Long userId) {
//        DocumentType dt = DocumentType.valueOf(type);
//        documentService.createDocumentForInstitute(file, dt, userId);
//    }
//
//    @GetMapping(InstituteRoute.FILE_DOWNLOAD)
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,
//                                                 @RequestParam("userId") Long userId,
//                                                 HttpServletRequest request) {
//        Resource resource = documentService.downloadFile(fileName, userId);
//
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            log.info("Could not determine file type.");
//        }
//
//        if (contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
//
//    @GetMapping(InstituteRoute.FILE_LIST)
//    public List<DocumentResource> getAllDocuments(@RequestParam("type") String type,
//                                                  @RequestParam("user_id") Long userId) {
//        DocumentType dt = DocumentType.valueOf(type);
//        return documentService.findAllDocuments(userId, dt);
//    }

}
