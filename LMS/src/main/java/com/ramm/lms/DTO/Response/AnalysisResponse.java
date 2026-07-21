package com.ramm.lms.DTO.Response;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisResponse {

    private List<String> importantTopics;

    private List<String> repeatedConcepts;

    private List<String> predictedQuestions;

    private String difficulty;

    private Map<String, String> weightage;
}