package com.dms.assistant.backend.models;

import com.dms.assistant.backend.models.email.EmailAddress;
import com.dms.assistant.backend.models.email.EmailMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.assertj.core.api.SoftAssertions;

import java.util.ArrayList;

import static com.dms.assistant.backend.constants.Constants.*;

public class Remind extends Email {

    public Remind(@JsonProperty(value = "Content") EmailMessage messageBody,
                  @JsonProperty(value = "From") EmailAddress mailSender,
                  @JsonProperty(value = "To") ArrayList<EmailAddress> mailReceivers) {
        super(messageBody, mailSender, mailReceivers);
    }

    public void checkValidData() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(getText()).as("Not expected remind text").isEqualTo(VALID_REMIND_TEXT);
        softly.assertThat(getSubjects().size()).as("Not expected number of subjects in remind").isEqualTo(EXPECTED_NUMBER_OF_SUBJECTS);
        softly.assertThat(getSubjects().get(0)).as("Not expected remind subject").isEqualTo(VALID_REMIND_SUBJECT);
        softly.assertThat(getSender()).as("Not expected sender address").isEqualTo(VALID_EMAIL_SENDER_ADDRESS);
        for (String receiver : getReceivers()) {
            softly.assertThat(EMPLOYEES.getEmployeeByInsuranceType(Insurance.InsuranceType.NONE).getEmployeesEmails()).as("Not expected receiver").contains(receiver);
        }
        softly.assertAll();
    }
}
