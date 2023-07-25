package com.example.monara_backend.service;

import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.repository.PdfBillRepo;
import com.example.monara_backend.repository.PdfGetFileRepo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @Autowired
    private PdfGetFileRepo pdfGetFileRepo;

    private final String FOLDER_PATH="C:\\Users\\milin\\Documents\\SPRING\\GIT12\\testfolder\\";


//get all pdf files
    public List<PdfBillSave> getAllPdf() {
        return pdfFileRepository.findAll();
    }

//get pdf file
    public byte[] downloadFileFromFileSystem(String filename) throws IOException {
        Optional<PdfBillSave> fileData = pdfGetFileRepo.findByName(filename);
        String filePath=fileData.get().getFilepath();
        byte[] filePdf = Files.readAllBytes(new File(filePath).toPath());
        return filePdf;
    }

    //upload pdf file
    public String uploadFileToFileSystem(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString();
        String filename = uuid + "." + extension;
        String filepath = FOLDER_PATH + filename;

        PdfBillSave fileData = pdfFileRepository.save(PdfBillSave.builder()
                .filename(filename)
                .type(file.getContentType())
                .filepath(filepath).build());

        file.transferTo(new File(filepath));

        if (fileData != null) {
            return "File uploaded successfully: " + filename;
        }
        return null;
    }

}
