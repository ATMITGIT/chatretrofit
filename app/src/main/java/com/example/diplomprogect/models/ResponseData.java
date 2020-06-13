package com.example.diplomprogect.models;

import com.google.gson.annotations.SerializedName;

public class ResponseData {
    @SerializedName("user")
    private User user;

    private String token;
    //----------------
    public ResponseData( User data, String token) {

        this.user = data;
        this.token = token;
    }



    public User getData() {
        return user;
    }

    public void setData(User data) {
        this.user = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }





}
