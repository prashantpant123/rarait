package com.rarait.education.document;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final InstituteLoginSession instituteLoginSession;
    private final DocumentUploadService documentUploadService;
    private final DocumentValidator documentValidator;

    public DocumentServiceImpl(DocumentRepository documentRepository,
                               InstituteLoginSession instituteLoginSession,
                               DocumentUploadService documentUploadService,
                               DocumentValidator documentValidator) {
        this.documentRepository = documentRepository;
        this.instituteLoginSession = instituteLoginSession;
        this.documentUploadService = documentUploadService;
        this.documentValidator = documentValidator;
    }

    @Override
    public List<DocumentResource> findAllDocuments(Long userId, DocumentType type) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        List<Document> documents = documentRepository.findAllByInstituteAndUser(institute.getId(), userId, type, Status.ACTIVE);
        return DocumentConvert.convertList(documents);
    }

    @Override
    public List<Document> getDocumentForInstitute(Long userId, DocumentType type, int instituteId) {
        return documentRepository.findAllByInstituteAndUser(instituteId, userId, type, Status.ACTIVE);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public String createDocumentForInstitute(MultipartFile file, DocumentType type, Long userId, Institute institute) {
        return createDocument(file, type, userId, institute);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public String createDocumentForAdmin(MultipartFile file, DocumentType type, Institute institute) {
        return createDocument(file, type, 0l, institute);
    }

    private String createDocument(MultipartFile file, DocumentType type, Long userId, Institute institute) {
        documentValidator.validate(file.getOriginalFilename());

        Document document = new Document();
        document.setInstitute(institute);
        document.setType(type);
        document.setStatus(Status.ACTIVE);
        document.setUserId(userId);
        document = documentRepository.save(document);

        String fileName = documentUploadService.storeFile(file, document.getId(), type);
        document.setFilename(fileName);
        documentRepository.save(document);
        return fileName;
    }

    @Override
    @Transactional
    public Resource downloadFile(String filename, Long userId, Integer instituteId) {
        Optional<Document> documents = documentRepository.findOneByInstituteAndUser(instituteId, filename, userId);
        Document document = documents.orElseThrow(() -> new ClientRestException("Document not found!"));
        return documentUploadService.loadFileAsResource(document.getFilename(), document.getType());
    }

    @Override
    @Transactional
    public Resource downloadFile(String filename, Integer instituteId) {
        Optional<Document> documents = documentRepository.findOneByInstitute(instituteId, filename);
        Document document = documents.orElseThrow(() -> new ClientRestException("Document not found!"));
        return documentUploadService.loadFileAsResource(document.getFilename(), document.getType());
    }

    @Override
    @Transactional
    public Resource downloadFileForAdmin(String filename, Integer instituteId) {
        Optional<Document> documents = documentRepository.findOneByInstitute(instituteId, filename);
        Document document = documents.orElseThrow(() -> new ClientRestException("Document not found!"));
        return documentUploadService.loadFileAsResource(document.getFilename(), document.getType());
    }
}
