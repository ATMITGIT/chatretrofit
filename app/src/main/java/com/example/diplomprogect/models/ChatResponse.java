package com.example.diplomprogect.models;

import java.util.List;

public class ChatResponse {

    private boolean status;
    private List<Chat> data;

    public ChatResponse(boolean status, List<Chat> data) {
        this.status = status;
        this.data = data;
    }

    public boolean isStatus() { return status; }

    public List <Chat> getChatData() { return data; }
}
