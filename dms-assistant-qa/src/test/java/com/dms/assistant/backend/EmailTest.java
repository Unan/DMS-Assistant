package com.dms.assistant.backend;

import com.dms.assistant.ConfigDMS;
import com.dms.assistant.backend.models.Notification;
import com.dms.assistant.backend.models.Remind;
import com.dms.assistant.backend.steps.FakeSMTPSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

@ContextConfiguration(classes = ConfigDMS.class)
@Epic("Services")
@Feature("Email sending test")
public class EmailTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private FakeSMTPSteps fakeSMTPSteps;

    @Test(groups = {"Services", "Smoke"}, description = "Check that all notifications contains valid data")
    public void notificationsValidDataTest() {
        fakeSMTPSteps.sendNotification();
        List<Notification> response = fakeSMTPSteps.getNotification();
        fakeSMTPSteps.checkNotificationsValid(response);
    }

    @Test(groups = {"Services", "Smoke"}, description = "Check that all reminds contains valid data")
    public void remindsValidDataTest() {
        fakeSMTPSteps.sendReminds();
        List<Remind> response = fakeSMTPSteps.getReminds();
        if (fakeSMTPSteps.isRemindsShouldExist(response)) {
            fakeSMTPSteps.checkRemindsValid(response);
        }
    }

    @AfterMethod(groups = {"Services"}, description = "Restoring data in SMTP server")
    public void restoreEmails() {
        fakeSMTPSteps.deleteEmails();
    }
}
