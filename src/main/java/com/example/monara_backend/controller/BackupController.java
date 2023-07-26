package com.example.monara_backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/backup")
@EnableScheduling

public class BackupController {
    @PostMapping("/back")
    public ResponseEntity<String> createBackup() {
        String path = "C:\\Users\\milin\\Documents\\SPRING\\GIT12\\Monara-backup"; // Change this to your path
        String dbName = "monara";
        String dbUser = "root";

        // Generate a timestamp for the backup file name
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = currentTime.format(formatter);

        String backupFileName = path + "\\Monara_backup_" + timestamp + ".sql";

        String executeCmd = "mysqldump -u"+" "+dbUser+" "+dbName+" "+"-r"+" "+backupFileName;


        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                return ResponseEntity.ok("Backup created successfully");
            } else {
                InputStream errorStream = runtimeProcess.getErrorStream();
                InputStreamReader isr = new InputStreamReader(errorStream);
                StringBuilder sb=new StringBuilder();
                int ch;
                while((ch = isr.read()) != -1)
                    sb.append((char)ch);
                throw new RuntimeException("Could not create backup "+sb.toString());
            }
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException("Error while creating backup", ex);
        }
    }


    @Scheduled(cron = "0 39 03 * * ?")
    public void scheduleBackup() {
        try {
            createBackup();
            System.out.println("Backup created successfully at scheduled time.");
        } catch (RuntimeException ex) {
            System.err.println("Error while creating scheduled backup: " + ex.getMessage());
        }
    }
}
