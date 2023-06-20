package com.example.monara_backend.dto;

import com.example.monara_backend.model.Designer;
import jakarta.persistence.*;

@Entity
public class ShowroomFileDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "docfile")
    private byte[] docfile;

    @Lob

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getDocfile() {
        return docfile;
    }

    public void setDocfile(byte[] docfile) {
        this.docfile = docfile;
    }

    public void setFileName(String name) {

    }

    public void setShowroomDocFile(byte[] bytes) {
    }

    public String getFileName() {
        return null;
    }

    public Object getDocFile() {
        return null;
    }

    public void save(Designer map) {
    }
}
