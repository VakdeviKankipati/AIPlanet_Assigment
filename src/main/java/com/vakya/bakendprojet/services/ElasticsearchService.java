package com.vakya.bakendprojet.services;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticsearchService {
    @Autowired
    private RestHighLevelClient client;

    // Searches for relevant sections in the document by ID using Elasticsearch
    public List<String> searchDocument(Long documentId, SearchSourceBuilder searchSourceBuilder) throws IOException {
        SearchRequest searchRequest = new SearchRequest("documents"); // Index name
        searchRequest.source(searchSourceBuilder);

        // Execute search request
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        List<String> results = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            results.add(hit.getSourceAsString());
        }

        return results;
    }
}
