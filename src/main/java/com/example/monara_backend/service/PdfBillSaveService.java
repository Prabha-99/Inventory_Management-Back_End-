package com.example.monara_backend.service;

import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.repository.PdfBillRepo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
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


    public ResponseEntity<byte[]> getPdf(String filename) throws IOException {
        Path path = Paths.get("C:\\Users\\milin\\Documents\\SPRING\\GIT12\\testfolder\\" + filename);
        byte[] bytes = Files.readAllBytes(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline").filename(filename).build());
        return ResponseEntity.ok().headers(headers).body(bytes);
    }

    public List<PdfBillSave> getAllPdf() {
        return pdfFileRepository.findAll();
    }

//    public void deleteBillPdf (Long bill_id) {
//        pdfFileRepository.deleteById(bill_id);
//    }


}
