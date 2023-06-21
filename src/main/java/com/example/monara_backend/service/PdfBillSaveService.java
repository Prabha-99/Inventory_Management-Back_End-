package com.example.monara_backend.service;

import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.repository.PdfBillRepo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


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


//    public byte[] getPdfFile(Integer bill_id) throws IOException {
//        Optional<PdfBillSave> pdfFile = pdfFileRepository.findById(bill_id);
//        String filePath = pdfFile.toString();
//        File file = new File(filePath);
//        byte[] fileBytes = Files.readAllBytes(file.toPath());
//        return fileBytes;
//    }

    public byte[] getPdfFileByPath(String filepath) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            throw new FileNotFoundException("PDF file not found: " + filepath);
        }
        return Files.readAllBytes(file.toPath());
    }



    public List<PdfBillSave> getAllPdf() {
        return pdfFileRepository.findAll();
    }

    public void deleteBillPdf (Integer bill_id) {
        pdfFileRepository.deleteById(bill_id);
    }



}
