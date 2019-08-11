package com.dms.assistant.backend.models;

import com.dms.assistant.backend.models.email.EmailAddress;
import com.dms.assistant.backend.models.email.EmailMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.assertj.core.api.SoftAssertions;

import java.util.ArrayList;
import java.util.List;

public abstract class Email {

    private final String EMAIL_TEXT;
    private final List<String> EMAIL_SUBJECTS;
    private final String EMAIL_SENDER;
    private final List<String> EMAIL_RECEIVERS;

    public Email(@JsonProperty(value = "Content") EmailMessage messageBody,
                 @JsonProperty(value = "From") EmailAddress mailSender,
                 @JsonProperty(value = "To") ArrayList<EmailAddress> mailReceivers) {

        this.EMAIL_TEXT = messageBody.getMessageBody();
        this.EMAIL_SUBJECTS = messageBody.getSubjects();
        this.EMAIL_SENDER = mailSender.getFullAddress();

        List<String> receivers = new ArrayList();
        for (EmailAddress mailAddress : mailReceivers) {
            receivers.add(mailAddress.getFullAddress());
        }
        this.EMAIL_RECEIVERS = receivers;
    }

    public String getText() {
        return EMAIL_TEXT;
    }

    public List<String> getSubjects() {
        return EMAIL_SUBJECTS;
    }

    public String getSender() {
        return EMAIL_SENDER;
    }

    public List<String> getReceivers() {
        return EMAIL_RECEIVERS;
    }

    public void checkFieldsNotEmpty() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(getText()).as("Empty email text").isNotEmpty();
        softly.assertThat(getSubjects()).as("Empty email subject").isNotEmpty();
        softly.assertThat(getSender()).as("Empty email sender").isNotEmpty();
        softly.assertThat(getReceivers()).as("Empty email receivers").isNotEmpty();
        softly.assertAll();
    }

    public abstract void checkValidData();
}
