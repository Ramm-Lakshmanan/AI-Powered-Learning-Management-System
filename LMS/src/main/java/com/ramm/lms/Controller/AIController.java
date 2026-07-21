package com.ramm.lms.Controller;

import com.ramm.lms.DTO.Response.AnalysisResponse;
import com.ramm.lms.DTO.Response.QuizResponse;
import com.ramm.lms.DTO.Response.SummaryResponse;
import com.ramm.lms.Service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    @PostMapping("/summary/{materialId}")
    public ResponseEntity<SummaryResponse> summarize(
            @PathVariable String materialId) {

        return ResponseEntity.ok(
                aiService.summarize(materialId)
        );
    }

    @PostMapping("/quiz/{materialId}")
    public ResponseEntity<QuizResponse> generateQuiz(
            @PathVariable String materialId) {

        return ResponseEntity.ok(
                aiService.generateQuiz(materialId)
        );
    }

    @PostMapping("/analyze/{materialId}")
    public ResponseEntity<AnalysisResponse> analyzePYQ(
            @PathVariable String materialId){

        return ResponseEntity.ok(
                aiService.analyzePYQ(materialId)
        );
    }
}