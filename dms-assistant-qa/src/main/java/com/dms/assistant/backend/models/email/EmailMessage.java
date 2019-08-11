package com.dms.assistant.backend.models.email;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EmailMessage {

    private String messageBody;
    private EmailSubjectList subjects;

    public EmailMessage(@JsonProperty(value = "Body") String messageBody,
                        @JsonProperty(value = "Headers") EmailSubjectList mailSubjectList) {
        this.messageBody = messageBody;
        this.subjects = mailSubjectList;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public List<String> getSubjects() {
        return subjects.getSubjects();
    }
}
