package com.example.monara_backend.service;

import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.repository.PdfBillRepo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class PdfBillSaveService {

    @Autowired
    private PdfBillRepo pdfFileRepository;

    public PdfBillSave savePdf(MultipartFile file) throws IOException {
        String fileExtension = FilenameUtils.getExtension(Objects.requireNonNull(file.getOriginalFilename()));
        String filename = UUID.randomUUID().toString() + "." + fileExtension;
        String filePath = "C:\\Users\\milin\\Documents\\SPRING\\GIT12\\testfolder\\" + filename; //SET FILE PATH TO SAVE PDF
        File newFile = new File(filePath);
        newFile.getParentFile().mkdirs();
        file.transferTo(newFile);
        PdfBillSave pdf = new PdfBillSave(filename, filePath);
        return pdfFileRepository.save(pdf);
    }


    public byte[] getPdfFile(Integer bill_id) throws IOException {
        Optional<PdfBillSave> pdfFile = pdfFileRepository.findById(bill_id);
        String filePath = pdfFile.toString();
        File file = new File(filePath);
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        return fileBytes;
    }

    public List<PdfBillSave> getAllPdf() {
        return pdfFileRepository.findAll();
    }

//    public void deleteBillPdf (Integer bill_id) {
//        pdfFileRepository.deleteById(bill_id);
//    }



}
