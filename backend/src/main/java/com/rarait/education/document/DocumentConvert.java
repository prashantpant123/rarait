package com.rarait.education.document;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class DocumentConvert {

    private DocumentConvert() {
    }

    public static DocumentResource convert(Document document){
        return DocumentResource.builder()
                .id(document.getId())
                .filename(document.getFilename())
                .type(document.getType().toString())
                .build();
    }

    public static List<DocumentResource> convertList(List<Document> documents){
        return documents.stream()
                .map(DocumentConvert::convert)
                .collect(Collectors.toList());
    }
}
