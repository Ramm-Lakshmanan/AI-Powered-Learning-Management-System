package com.ramm.lms.Service.Impl;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.ramm.lms.Service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiServiceImpl implements GeminiService{
    private final Client geminiClient;

    @Value("${gemini.model}")
    private String model;

    @Override
    public String generateResponse(String prompt) {

        GenerateContentResponse response =
                geminiClient.models.generateContent(
                        model,
                        prompt,
                        null
                );

        return response.text();
    }
}
