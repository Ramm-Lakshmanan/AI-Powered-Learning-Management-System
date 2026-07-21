package com.ramm.lms.Service;

import com.ramm.lms.DTO.Response.AnalysisResponse;
import com.ramm.lms.DTO.Response.QuizResponse;
import com.ramm.lms.DTO.Response.SummaryResponse;

public interface AIService {

    SummaryResponse summarize(String materialId);

    QuizResponse generateQuiz(String materialId);

    AnalysisResponse analyzePYQ(String materialId);
}