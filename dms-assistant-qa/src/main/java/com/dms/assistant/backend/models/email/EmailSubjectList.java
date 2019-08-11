package com.dms.assistant.backend.models.email;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class EmailSubjectList {

    private List<String> subjects;

    public EmailSubjectList(@JsonProperty(value = "Subject") ArrayList<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getSubjects() {
        return subjects;
    }
}
