package com.dms.assistant.backend.steps;

import com.dms.assistant.backend.clients.AdminClient;
import com.dms.assistant.backend.clients.FakeSMTPClient;
import com.dms.assistant.backend.clients.LoginClient;
import com.dms.assistant.backend.models.*;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dms.assistant.backend.constants.Constants.ADMIN;
import static com.dms.assistant.backend.constants.Constants.EMPLOYEES;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class FakeSMTPSteps {

    @Autowired
    private FakeSMTPClient fakeSMTPClient;

    @Autowired
    private AdminClient adminClient;

    @Autowired
    private LoginClient loginClient;

    @Step("Check that reminds should exist")
    public boolean isRemindsShouldExist(List<Remind> reminds) {
        if (EMPLOYEES.getEmployeeByInsuranceType(Insurance.InsuranceType.NONE).size() == 0) {
            assertThat(reminds).as("Reminds should not exist").isEqualTo(null);
            return false;
        } else {
            return true;
        }
    }

    @Step("Get all notifications from SMTP server")
    public List<Notification> getNotification() {
        return fakeSMTPClient.getNotifications();
    }

    @Step("Get all remind from SMTP server")
    public List<Remind> getReminds() {
        return fakeSMTPClient.getReminds();
    }

    @Step("Delete all messages on SMTP server")
    public void deleteEmails() {
        fakeSMTPClient.deleteAllEmails();
    }

    @Step("Check that notification valid data")
    public void checkNotificationsValid(List<Notification> emails) {
        assertThat(emails.isEmpty()).as("Mail server is empty").isFalse();
        for (Email email : emails) {
            email.checkFieldsNotEmpty();
            email.checkValidData();
        }
    }

    @Step("Check that remind valid data")
    public void checkRemindsValid(List<Remind> emails) {
        assertThat(emails.isEmpty()).as("Mail server is empty").isFalse();
        for (Email email : emails) {
            email.checkFieldsNotEmpty();
            email.checkValidData();
        }
    }

    @Step("Send notifications")
    public void sendNotification() {
        Token token = loginClient.getEmployeeToken(ADMIN);
        adminClient.sendNotifications(token.getToken());
    }

    @Step("Send reminds")
    public void sendReminds() {
        Token token = loginClient.getEmployeeToken(ADMIN);
        adminClient.sendReminds(token.getToken());
    }
}
