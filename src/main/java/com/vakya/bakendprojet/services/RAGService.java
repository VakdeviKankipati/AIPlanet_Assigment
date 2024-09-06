package com.vakya.bakendprojet.services;

import com.vakya.bakendprojet.models.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RAGService {

    @Autowired
    private DocumentRetrievalService documentRetrievalService;  // Service to retrieve relevant document content

    @Autowired
    private NLPModelService nlpModelService;  // Service to interact with an NLP generation model

    public String queryDocument(String query, Document document) throws IOException {
        // Step 1: Retrieve relevant sections of the document using a retriever (like Elasticsearch)
        List<String> retrievedSections = documentRetrievalService.retrieveRelevantSections(query, document);

        // Step 2: Use a language model to generate a response based on the retrieved content
        String combinedContent = String.join(" ", retrievedSections);  // Combine relevant sections into a single context
        String generatedAnswer = nlpModelService.generateAnswer(query, combinedContent);

        return generatedAnswer;  // Return the generated response
    }
}
