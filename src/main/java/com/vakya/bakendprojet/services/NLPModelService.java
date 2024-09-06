package com.vakya.bakendprojet.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

@Service
public class NLPModelService {

    private final String OPENAI_API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private final String API_KEY = "your-openai-api-key";  // Replace with your OpenAI API key

    // Generates an answer based on the query and the retrieved document context
    public String generateAnswer(String query, String context) {
        String prompt = generatePrompt(query, context);
        String generatedAnswer = callNLPModel(prompt);
        return generatedAnswer;
    }

    // Generates a prompt by combining the user's query and the document context
    private String generatePrompt(String query, String context) {
        return "Context: " + context + "\nQuery: " + query + "\nAnswer:";
    }

    // Placeholder method to call an external NLP model (e.g., OpenAI GPT)
    private String callNLPModel(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        // Create the body for the API request
        String requestBody = "{ \"prompt\": \"" + prompt + "\", \"max_tokens\": 150 }";

        // Send the request to the OpenAI API
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, String.class);

        // Process the response from the NLP model
        return extractAnswerFromResponse(response.getBody());
    }

    // Extracts the answer from the OpenAI API response
    private String extractAnswerFromResponse(String responseBody) {
        // Implement logic to parse the response and extract the generated answer
        // This is a simplified placeholder; in practice, you'd parse the JSON response
        // For example, if using the OpenAI API, the response includes a 'choices' field with the answer
        return responseBody;  // Adjust as per actual API response structure
    }
}
