package com.example.monara_backend.service;

import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.repository.PdfBillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PdfBillSaveService {

    @Autowired
    private PdfBillRepo pdfFileRepository;

    public PdfBillSave uploadPdf (MultipartFile file) throws IOException {
        PdfBillSave pdfFile = new PdfBillSave();
        pdfFile.setContent(file.getBytes());
        return pdfFileRepository.save(pdfFile);
    }

    public List<PdfBillSave> getAllPDFs() {
        List<PdfBillSave> pdfs = pdfFileRepository.findAll();
        return pdfs.stream()
                .map(pdf -> new PdfBillSave(pdf.getId(), pdf.getContent()))
                .collect(Collectors.toList());
    }
//    public PdfBillSave getPDFById(Integer id) {
//        Optional<PdfBillSave> optionalPDF = pdfFileRepository.findById(id);
//        return optionalPDF.orElse(null);
//    }
}
