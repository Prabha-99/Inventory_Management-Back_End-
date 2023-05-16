package com.example.monara_backend.service;

import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.repository.PdfBillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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


    public byte[] getAllPdfs() throws IOException {
        List<PdfBillSave> pdfs = pdfFileRepository.findAll();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (PdfBillSave pdf : pdfs) {
            outputStream.write(pdf.getContent());
        }

        return outputStream.toByteArray();
    }

}
