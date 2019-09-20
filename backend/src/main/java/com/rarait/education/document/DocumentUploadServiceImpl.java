package com.rarait.education.document;

import com.rarait.education.shared.setting.DocumentSetting;
import com.rarait.education.utility.BaseConversion;
import com.rarait.framework.exception.ClientRestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class DocumentUploadServiceImpl implements DocumentUploadService {

    private final Path logo;
    private final Path staffPicture;
    private final Path staffDocument;
    private final Path studentPicture;
    private final Path studentDocument;

    private final List<String> extension = Arrays.asList("png", "jpg", "jpeg", "gif");

    public DocumentUploadServiceImpl(DocumentSetting documentSetting) {
        this.logo = Paths.get(documentSetting.getLogo()).toAbsolutePath().normalize();
        this.staffPicture = Paths.get(documentSetting.getStaffPicture()).toAbsolutePath().normalize();
        this.staffDocument = Paths.get(documentSetting.getStaffDocument()).toAbsolutePath().normalize();
        this.studentPicture = Paths.get(documentSetting.getStudentPicture()).toAbsolutePath().normalize();
        this.studentDocument = Paths.get(documentSetting.getStudentDocument()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.logo);
            Files.createDirectories(this.staffPicture);
            Files.createDirectories(this.staffDocument);
            Files.createDirectories(this.studentPicture);
            Files.createDirectories(this.studentDocument);
        } catch (Exception exe) {
            log.error("Error on creating file directory");
            throw new ClientRestException("Couldn't create directory for files.");
        }
    }

    @Override
    public String storeFile(MultipartFile file, long documentId, DocumentType type) {
        if (!extension.contains(FilenameUtils.getExtension(file.getOriginalFilename()))) {
            throw new ClientRestException("Invalid image file extension");
        }
        String newFileName = BaseConversion.getCode(documentId) + "-" + UUID.randomUUID().toString()
                + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = StringUtils.cleanPath(newFileName);
        try {
            if (fileName.contains("..")) {
                throw new ClientRestException("Filename contains invalid path sequence");
            }
            Path targetLocation = null;
            if (type.equals(DocumentType.LOGO)) {
                targetLocation = this.logo.resolve(fileName);
            } else if (type.equals(DocumentType.STAFF_PICTURE))
                targetLocation = this.staffPicture.resolve(fileName);
            else if (type.equals(DocumentType.STAFF_DOCUMENT))
                targetLocation = this.staffDocument.resolve(fileName);
            else if (type.equals(DocumentType.STUDENT_PICTURE))
                targetLocation = this.studentPicture.resolve(fileName);
            else if (type.equals(DocumentType.STUDENT_DOCUMENT))
                targetLocation = this.studentDocument.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ioe) {
            throw new ClientRestException("Couldn't upload file to remote location");
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName, DocumentType type) {
        Path filePath = null;
        try {
            if (type.equals(DocumentType.LOGO)) {
                filePath = this.logo.resolve(fileName).normalize();
            } else if (type.equals(DocumentType.STAFF_PICTURE))
                filePath = this.staffPicture.resolve(fileName).normalize();
            else if (type.equals(DocumentType.STAFF_DOCUMENT))
                filePath = this.staffDocument.resolve(fileName).normalize();
            else if (type.equals(DocumentType.STUDENT_PICTURE))
                filePath = this.studentPicture.resolve(fileName).normalize();
            else if (type.equals(DocumentType.STUDENT_DOCUMENT))
                filePath = this.studentDocument.resolve(fileName).normalize();

            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                throw new ClientRestException("File not found ");
            }
            return resource;
        } catch (MalformedURLException ex) {
            throw new ClientRestException("File not found ");
        }
    }
}
