package com.example.diplomprogect.models;

import java.io.InputStream;

public class Files {
    public Files(String originalName, String file, String ext, String mimeType, String size) {
        this.originalName = originalName;
        this.file = file;
        this.ext = ext;
        this.mimeType = mimeType;
        this.size = size;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    private String originalName;
    private String file;
    private String ext;
    private String mimeType;
    private String size;
}
