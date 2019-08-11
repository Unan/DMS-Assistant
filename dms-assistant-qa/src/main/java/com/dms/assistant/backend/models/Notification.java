package com.dms.assistant.backend.models;

import com.dms.assistant.backend.models.email.EmailAddress;
import com.dms.assistant.backend.models.email.EmailMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.assertj.core.api.SoftAssertions;

import java.util.ArrayList;

import static com.dms.assistant.backend.constants.Constants.*;

public class Notification extends Email {

    public Notification(@JsonProperty(value = "Content") EmailMessage messageBody,
                        @JsonProperty(value = "From") EmailAddress mailSender,
                        @JsonProperty(value = "To") ArrayList<EmailAddress> mailReceivers) {
        super(messageBody, mailSender, mailReceivers);
    }

    public void checkValidData() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(getText()).as("Not expected notification text").isEqualTo(VALID_NOTIFY_TEXT);
        softly.assertThat(getSubjects().size()).as("Not expected number of subjects in notification").isEqualTo(EXPECTED_NUMBER_OF_SUBJECTS);
        softly.assertThat(getSubjects().get(0)).as("Not expected notification subject").isEqualTo(VALID_NOTIFY_SUBJECT);
        softly.assertThat(getSender()).as("Not expected sender address in notification").isEqualTo(VALID_EMAIL_SENDER_ADDRESS);
        for (String receiver : getReceivers()) {
            softly.assertThat(EMPLOYEES.getEmployeesEmails()).as("Not expected receiver").contains(receiver);
        }
        softly.assertAll();
    }
}
