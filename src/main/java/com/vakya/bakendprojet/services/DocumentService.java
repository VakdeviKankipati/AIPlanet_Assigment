package com.vakya.bakendprojet.services;

import com.vakya.bakendprojet.exception.DocumentNotFoundException;
import com.vakya.bakendprojet.models.Document;
import com.vakya.bakendprojet.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {

    Document uploadDocument(MultipartFile file, User user) throws IOException;

    List<Document> getUserDocuments(User user);

    Document getDocumentById(Long documentId) throws DocumentNotFoundException;
}
