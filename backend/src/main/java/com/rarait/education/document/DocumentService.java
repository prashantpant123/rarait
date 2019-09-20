package com.rarait.education.document;

import com.rarait.education.institute.impl.Institute;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface DocumentService {

    List<DocumentResource> findAllDocuments(Long userId, DocumentType type);

    List<Document> getDocumentForInstitute(Long userId, DocumentType type, int instituteId);

    String createDocumentForInstitute(MultipartFile file, DocumentType type, Long userId, Institute institute);

    String createDocumentForAdmin(MultipartFile file, DocumentType type, Institute institute);

    Resource downloadFile(String filename, Long userId, Integer instituteId);

    Resource downloadFile(String filename, Integer instituteId);

    Resource downloadFileForAdmin(String filename, Integer instituteId);

}
