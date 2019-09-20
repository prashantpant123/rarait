package com.rarait.education.document;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface DocumentUploadService {
    String storeFile(MultipartFile file, long documentId, DocumentType type);

    Resource loadFileAsResource(String fileName, DocumentType type);
}
