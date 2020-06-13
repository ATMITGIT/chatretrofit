package com.example.diplomprogect.models;


import com.google.gson.annotations.SerializedName;

public class ChatData {

    @SerializedName("status")
    private boolean status;

    @SerializedName("data")
    ResponseData response;


    public boolean isStatus() {
        return status;
    }

    public ChatData(boolean status, ResponseData response) {
        this.status = status;
        this.response = response;
    }

    public ResponseData getResponse() {
        return response;
    }

    public void setResponse(ResponseData response) {
        this.response = response;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }




}
