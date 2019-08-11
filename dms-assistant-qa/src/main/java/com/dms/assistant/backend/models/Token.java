package com.dms.assistant.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class Token {

    @NotEmpty
    private String token;

    public Token() {
    }

    public Token(@JsonProperty(required = true) String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}