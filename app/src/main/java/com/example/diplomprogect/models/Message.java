package com.example.diplomprogect.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Message {
    private String id;

    private String receiver;
    private String message;
    private String createdAt;
    private List<Files> files;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getReciver() {
        return receiver;
    }

    public void setReciver(String reciver) {
        this.receiver = reciver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<Files> getFiles() {
        return files;
    }

    public void setFiles(List<Files> files) {
        this.files = files;
    }
    public Message(String id, String reciver, String message, String createdAt, List<Files> files) {
        this.id = id;
        this.receiver= reciver;
        this.message = message;
        this.createdAt = createdAt;
        this.files = files;
    }




}
