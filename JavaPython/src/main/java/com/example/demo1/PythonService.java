package com.example.demo1;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

@Service
public class PythonService {

    private final String flaskUrl = "http://127.0.0.1:5000/process"; // Flask API URL

    public void sendDataToFlask() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            // Create request body
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("message", "Ankit"); // Sending message to Flask
            
            // Convert to JSON			aa
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = objectMapper.writeValueAsString(requestBody);
            
            // Create HTTP entity
            HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);
            
            // Send POST request to Flask API
            ResponseEntity<String> response = restTemplate.postForEntity(flaskUrl, request, String.class);
            
            // Parse JSON response
            JsonNode jsonResponse = objectMapper.readTree(response.getBody());
            String modifiedMessage = jsonResponse.get("modifiedMessage").asText();
            
            // Print modified message received from Flask
            System.out.println("This is Modified : \n" + modifiedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
