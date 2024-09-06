package com.vakya.bakendprojet.services;

import com.vakya.bakendprojet.models.Document;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentRetrievalService {
    @Autowired
    private ElasticsearchService elasticsearchService;

    // Retrieves relevant sections from the document by querying the document content using Elasticsearch
    public List<String> retrieveRelevantSections(String query, Document document) throws IOException {
        // Create a search request using Elasticsearch to find relevant sections in the document content
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("content", query));

        // Call Elasticsearch service to search the document content (assuming the document is indexed)
        List<String> relevantSections = elasticsearchService.searchDocument(document.getId(), searchSourceBuilder);

        // Return the retrieved sections (this is placeholder logic, adjust based on actual Elasticsearch setup)
        return relevantSections.isEmpty() ? List.of("No relevant sections found") : relevantSections;
    }


}
