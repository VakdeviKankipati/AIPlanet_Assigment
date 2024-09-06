package com.vakya.bakendprojet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@Service
public class NLPService {
    private final RestTemplate restTemplate;

    @Autowired
    public NLPService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public String parseDocument(MultipartFile file) throws IOException {
        // Define the URL of the NLP service or unstructured.io endpoint
        String url = "https://api.unstructured.io/v1/process"; // Replace with the actual URL

        // Convert the file to byte array to send as request body
        byte[] fileBytes = file.getBytes();

        // Set the headers for the HTTP request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Create the HTTP entity including file and headers
        HttpEntity<byte[]> requestEntity = new HttpEntity<>(fileBytes, headers);

        // Make the request and get the response from unstructured.io or another NLP API
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        // Return the parsed content (or you can process the response as needed)
        return response.getBody();
    }
}
