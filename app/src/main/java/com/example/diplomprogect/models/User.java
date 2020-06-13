package com.example.diplomprogect.models;

public class User {

    private int id;
    private String nickname, login;

    public User(int id, String nickname, String login) {
        this.id = id;
        this.nickname = nickname;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLogin() {
        return login;
    }
}
