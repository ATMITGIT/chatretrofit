package com.example.diplomprogect.models;

public class LoginResponse {


    private boolean status;
    private User data;




    public LoginResponse(boolean status, User data) {
        this.status = status;
        this.data = data;


    }

    public boolean isStatus() {
        return status;
    }

    public User getData() {
        return data;
    }

}
