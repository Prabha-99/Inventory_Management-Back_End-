package com.example.monara_backend.service;

import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.repository.PdfBillRepo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
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
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(fileName);
        String newFileName = UUID.randomUUID().toString() + "." + fileExtension;
        String filePath = "C:\\Users\\milin\\Documents\\SPRING\\GIT12\\testfolder\\" + newFileName;
        File newFile = new File(filePath);
        newFile.getParentFile().mkdirs();
        file.transferTo(newFile);
        PdfBillSave pdf = new PdfBillSave(fileName, filePath);
        return pdfFileRepository.save(pdf);
    }

//    public byte[] getPdfFile(String filePath) throws IOException {
//        File file = new File(filePath);
//        return Files.readAllBytes(file.toPath());
//    }




}
