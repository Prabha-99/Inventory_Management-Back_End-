package com.example.monara_backend.service;

import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.repository.PdfBillRepo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class PdfBillSaveService {
    private final PdfBillRepo pdfFileRepository;

    public PdfBillSaveService(PdfBillRepo pdfFileRepository) {
        this.pdfFileRepository = pdfFileRepository;
    }

    public PdfBillSave uploadPdf(MultipartFile file) throws IOException {
        PdfBillSave pdfFile = new PdfBillSave();
        pdfFile.setContent(file.getBytes());
        return pdfFileRepository.save(pdfFile);
    }

    public byte[] getAllPdfs() throws IOException {
        List<PdfBillSave> pdfs = pdfFileRepository.findAll();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (PdfBillSave pdf : pdfs) {
            outputStream.write(pdf.getContent());
        }

        return outputStream.toByteArray();
    }
}
