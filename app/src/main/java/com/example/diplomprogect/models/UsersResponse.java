package com.example.diplomprogect.models;

import java.util.List;

public class UsersResponse {

    private boolean status;
    private List<User> data;

    public UsersResponse(boolean status, List<User> data) {
        this.status = status;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public List<User> getData() {
        return data;
    }
}
