package com.ramm.lms.Service.Impl;

import com.ramm.lms.DTO.Response.AnalysisResponse;
import tools.jackson.databind.ObjectMapper;
import com.ramm.lms.DTO.Response.QuizResponse;
import com.ramm.lms.DTO.Response.SummaryResponse;
import com.ramm.lms.Entity.Material;
import com.ramm.lms.Repository.MaterialRepository;
import com.ramm.lms.Service.AIService;
import com.ramm.lms.Service.GeminiService;
import com.ramm.lms.Service.PDFService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final MaterialRepository materialRepository;
    private final PDFService pdfService;
    private final GeminiService geminiService;
    private final ObjectMapper objectMapper;

    @Override
    public SummaryResponse summarize(String materialId) {
        Material material = materialRepository.findById(materialId).orElseThrow(() ->
                new RuntimeException("Material not found"));

        String pdfText=pdfService.extractText(material.getFilePath());

        String prompt = """
            You are an expert teacher.

            Read the following study material and generate a clear summary.

            Requirements:

            - Maximum 10 bullet points.
            - Easy for students to revise.
            - Include important definitions.
            - Mention important concepts.
            - Ignore page numbers and headers.

            Study Material:

            """ + pdfText;

        String summary =
                geminiService.generateResponse(prompt);

        return SummaryResponse.builder()
                .summary(summary)
                .build();
    }

    @Override
    public QuizResponse generateQuiz(String materialId) {

        Material material = materialRepository.findById(materialId)
                .orElseThrow(() ->
                        new RuntimeException("Material not found"));

        String pdfText = pdfService.extractText(material.getFilePath());

        String prompt = """
            You are an expert university professor.

            Generate exactly 10 multiple choice questions from the study material.

            Return ONLY valid JSON.

            Use this exact schema:

            {
              "questions":[
                {
                  "question":"",
                  "optionA":"",
                  "optionB":"",
                  "optionC":"",
                  "optionD":"",
                  "correctAnswer":""
                }
              ]
            }

            Rules:
            - No markdown
            - No explanation
            - No code block
            - No extra text
            - Return valid JSON only.

            Study Material:

            """ + pdfText;

        String response =
                geminiService.generateResponse(prompt);
        response = response.replace("```json", "")
                .replace("```", "")
                .trim();
        try {

            return objectMapper.readValue(
                    response,
                    QuizResponse.class
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to parse Gemini quiz response.",
                    e
            );
        }
    }

    @Override
    public AnalysisResponse analyzePYQ(String materialId){

        Material material = materialRepository.findById(materialId)
                .orElseThrow(() ->
                        new RuntimeException("Material not found"));

        String pdfText =
                pdfService.extractText(material.getFilePath());

        String prompt = """
            You are an experienced university professor.
            
            Analyze this previous year question paper.
            
            Return ONLY valid JSON.
            
            Schema:
            
            {
              "importantTopics":[
                ""
              ],
              "repeatedConcepts":[
                ""
              ],
              "predictedQuestions":[
                ""
              ],
              "difficulty":"",
              "weightage":{
                  "Unit 1":"",
                  "Unit 2":"",
                  "Unit 3":"",
                  "Unit 4":"",
                  "Unit 5":""
              }
            }
            
            Rules:
            
            - Return only JSON.
            - No markdown.
            - No explanation.
            - Weightage should be percentages.
            - Predict 5 likely questions.
            
            Question Paper:
            
            """ + pdfText;

        String response =
                geminiService.generateResponse(prompt);

        response = response.replace("```json","")
                .replace("```","")
                .trim();

        try{

            return objectMapper.readValue(
                    response,
                    AnalysisResponse.class);

        }catch(Exception e){

            throw new RuntimeException(
                    "Unable to parse Gemini analysis.",
                    e);
        }
    }
}