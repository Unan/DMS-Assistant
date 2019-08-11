package com.dms.assistant.backend.models;

public class Auth {

    private String login;

    public Auth(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public Auth setLogin(String login) {
        this.login = login;
        return this;
    }
}
