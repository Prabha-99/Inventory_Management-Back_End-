package com.example.monara_backend.dto;

public class ShowroomFileUploadResponse {

    private String fileName;

    private String contentType;

    private String url;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ShowroomFileUploadResponse(String fileName, String contentType, String url){
        this.fileName = fileName;
        this.contentType = contentType;
        this.url = url;
    }
}
