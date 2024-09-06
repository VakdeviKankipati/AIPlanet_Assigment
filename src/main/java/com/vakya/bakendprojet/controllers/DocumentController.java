package com.vakya.bakendprojet.controllers;

import com.vakya.bakendprojet.exception.DocumentNotFoundException;
import com.vakya.bakendprojet.models.Document;
import com.vakya.bakendprojet.models.User;
import com.vakya.bakendprojet.services.DocumentService;
import com.vakya.bakendprojet.services.RAGService;
import com.vakya.bakendprojet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private RAGService ragService;
    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<Document> uploadDocument(@RequestParam("file") MultipartFile file, Principal principal) throws IOException, IOException {
        User user = userService.findByUsername(principal.getName());

        // Upload the document and associate it with the user
        Document document = documentService.uploadDocument(file, user);
        return ResponseEntity.ok(document);
    }

    @GetMapping("/query")
    public ResponseEntity<String> queryDocument(@RequestParam("documentId") Long documentId, @RequestParam("query") String query) throws DocumentNotFoundException, IOException {
        // Retrieve the document by its ID
        Document document = documentService.getDocumentById(documentId);

        // Use RAGService to query the document content
        String result = ragService.queryDocument(query, document);

        return ResponseEntity.ok(result);
    }
}
