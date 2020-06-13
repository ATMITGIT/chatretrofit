package com.example.diplomprogect.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CallChat {
    public CallChat(Chat chat, String pages, List<Message> list) {
        this.chat = chat;
        this.pages = pages;
        this.list = list;
    }

    @SerializedName("friend")
    private Chat chat;
    @SerializedName("pages")
    private String pages;
    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }


    @SerializedName("messages")
    List<Message> list;
    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }




    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }




}
