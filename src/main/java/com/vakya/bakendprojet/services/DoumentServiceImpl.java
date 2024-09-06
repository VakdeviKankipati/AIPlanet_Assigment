package com.vakya.bakendprojet.services;

import com.vakya.bakendprojet.exception.DocumentNotFoundException;
import com.vakya.bakendprojet.models.Document;
import com.vakya.bakendprojet.models.User;
import com.vakya.bakendprojet.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DoumentServiceImpl implements DocumentService{

    private DocumentRepository documentRepository;
    private S3Service s3Service;
    private NLPService nlpService;

    public DoumentServiceImpl(DocumentRepository documentRepository, S3Service s3Service, NLPService nlpService){
        this.documentRepository=documentRepository;
        this.s3Service=s3Service;
        this.nlpService=nlpService;

    }


    @Override
    public Document uploadDocument(MultipartFile file, User user) throws IOException {
        String fileUrl = s3Service.uploadFile(file);
        String parsedContent = nlpService.parseDocument(file);

        Document document = new Document();
        document.setUser(user);
        document.setFileName(file.getOriginalFilename());
        //document.setFileType(file.getContentType());
        document.setUrl(fileUrl);
        //document.setParsedContent(parsedContent);

        return documentRepository.save(document);
    }

    @Override
    public List<Document> getUserDocuments(User user) {
        return documentRepository.findByUser(user);
    }

    public Document getDocumentById(Long documentId) throws DocumentNotFoundException {
        // Fetch the document by its ID from the repository
        return documentRepository.findById(documentId)
                .orElseThrow(() -> new DocumentNotFoundException("Document not found"));
    }
}
