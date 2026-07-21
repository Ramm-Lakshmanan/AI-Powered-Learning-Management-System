package com.ramm.lms.Service.Impl;

import com.ramm.lms.Service.PDFService;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor

public class PDFServiceImpl implements PDFService {
    @Override
    public String extractText(String filePath) {

        try(PDDocument doc = Loader.loadPDF(new File(filePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(doc);
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to extract text from PDF.", e);
        }
    }
}
